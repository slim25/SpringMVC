package mentorship.program.listener;

import mentorship.program.jms.components.MessageSender;
import mentorship.program.model.persistance.LogginAction;
import mentorship.program.service.JMManagementService;
import mentorship.program.service.impl.JMManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserJMSStatusListener implements ApplicationListener{

    @Autowired
    private MessageSender messageSener;
    @Autowired
    private JMManagementService jmManagementService;

    @Override
    public void onApplicationEvent(ApplicationEvent appEvent){
        if (appEvent instanceof AuthenticationSuccessEvent){
            AuthenticationSuccessEvent event = (AuthenticationSuccessEvent) appEvent;
            UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();

            String eventInfo = "Event : " + AuthenticationSuccessEvent.class.getName();

            jmManagementService.saveLogInOutInfo(LogginAction.IN,userDetails.getUsername(), eventInfo, new Date());
            messageSener.sendMessageWithDefaultParameter(eventInfo);

        }else if(appEvent instanceof AbstractAuthenticationFailureEvent){
            AbstractAuthenticationFailureEvent event = (AbstractAuthenticationFailureEvent) appEvent;
            String userName =  event.getAuthentication().getName();

            String eventInfo = "Event : " + AbstractAuthenticationFailureEvent.class.getName();

            jmManagementService.saveLogInOutInfo(LogginAction.FAILURE, userName, eventInfo, new Date());
            messageSener.sendMessageWithDefaultParameter(eventInfo);
        }

    }


}
