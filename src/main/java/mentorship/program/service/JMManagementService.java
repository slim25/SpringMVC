package mentorship.program.service;


import mentorship.program.model.LogginInfo;
import mentorship.program.model.persistance.LogginAction;

import java.util.Date;
import java.util.List;

public interface JMManagementService {

    void saveLogInOutInfo(LogginAction action, String userName, String info, Date date);

    List<LogginInfo> getAllLogInInfos();

}
