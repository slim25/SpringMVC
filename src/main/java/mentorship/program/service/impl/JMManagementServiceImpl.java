package mentorship.program.service.impl;

import mentorship.program.dao.impl.JMManagementDaoImpl;
import mentorship.program.jms.components.MessageSender;
import mentorship.program.model.LogginInfo;
import mentorship.program.model.persistance.LogginAction;
import mentorship.program.service.JMManagementService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;

import static mentorship.program.jms.components.MessageSender.ADMIN_AUTHENTICATION_MANAGER_NAME;
import static mentorship.program.jms.components.MessageSender.ADMIN_DESTINATION_BOX;

@Service
public class JMManagementServiceImpl implements JMManagementService{

    @Autowired
    private JMManagementDaoImpl jmManagementDao;
    @Autowired
    private MessageSender messageSener;

    @Override
    public void saveLogInOutInfo(LogginAction action, String userName, String info, Date date) {
        LogginInfo logInfo = jmManagementDao.getLogInfoByUserName(userName);
        if(logInfo == null){
            logInfo = new LogginInfo(action,  info, userName, date, null);
            if(action.FAILURE.equals(action))   logInfo.incrInsuccessfullCount();
        }else {
            switch (action) {
                case IN: {
                    logInfo.setLogInDate(date);
                    break;
                }
                case OUT: {
                    logInfo.setLogOutDate(date);
                    Long durationTime = logInfo.getLogInDurationTime();
                    Duration calculatedDuration = calculateUserLoginDurationTime(durationTime, logInfo.getLogInDate(), logInfo.getLogOutDate());
                    logInfo.setLogInDurationTime(calculatedDuration.getSeconds());
                    break;
                }
                case FAILURE: {
                    logInfo.setLogInDate(date);
                    boolean logInFailCountReseted = logInfo.incrInsuccessfullCount();
                    if(logInFailCountReseted){
                        messageSener.sendMessageWithDefaultParameter(info);
                        messageSener.sendFailureMessage(ADMIN_DESTINATION_BOX, ADMIN_AUTHENTICATION_MANAGER_NAME,info);
                    }
                    int failuresCount = jmManagementDao.getTotalLoginFailures();
                    if(failuresCount > 10) {
                        String msgToADmin = "Total login failures exceeds 10 ";
                        messageSener.sendMessageWithDefaultParameter(msgToADmin);
                        messageSener.sendFailureMessage(ADMIN_DESTINATION_BOX, ADMIN_AUTHENTICATION_MANAGER_NAME, msgToADmin);
                    }
                    break;
                }
            }
        }
        jmManagementDao.saveLogInOutInfo(logInfo);
    }

    @Override
    public List<LogginInfo> getAllLogInInfos() {
        return jmManagementDao.getAll();
    }

    private Duration calculateUserLoginDurationTime( Long previousDurationTime,  Date logInTime, Date logOutTime){
        Duration duration = Duration.between(logInTime.toInstant(),logOutTime.toInstant());

        long sumOfSeconds = duration.getSeconds();
        if(previousDurationTime != null){
            sumOfSeconds = duration.getSeconds() + previousDurationTime;
        }
        return Duration.ofSeconds(sumOfSeconds);
    }


}
