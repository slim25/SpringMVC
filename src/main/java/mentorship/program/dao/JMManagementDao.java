package mentorship.program.dao;

import mentorship.program.model.LogginInfo;

import java.util.List;

public interface JMManagementDao {

    void saveLogInOutInfo(LogginInfo logInfo);

    LogginInfo getLogInfoByUserName(String userName);

    int getTotalLoginFailures();

    List<LogginInfo> getAll();
}
