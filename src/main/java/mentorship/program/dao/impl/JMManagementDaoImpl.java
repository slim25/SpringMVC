package mentorship.program.dao.impl;

import mentorship.program.dao.JMManagementDao;
import mentorship.program.model.LogginInfo;
import mentorship.program.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class JMManagementDaoImpl implements JMManagementDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveLogInOutInfo(LogginInfo logInfo) {
        entityManager.merge(logInfo);
    }

    @Override
    public LogginInfo getLogInfoByUserName(String userName){
        LogginInfo logInfo = null;
        try {
            logInfo = entityManager
                    .createQuery("SELECT li FROM LogginInfo li WHERE li.userName = :userName", LogginInfo.class)
                    .setParameter("userName", userName)
                    .getSingleResult();
        }catch (NoResultException nre){
            System.out.println("No user with username : " + userName);        }
        return logInfo;
    }

    @Override
    public int getTotalLoginFailures() {
        Long result = entityManager
                .createQuery("SELECT COUNT(li.userName) FROM LogginInfo li WHERE li.insuccessfullLoginCount > 0", Long.class)
                .getSingleResult();
        return result.intValue();
    }

    @Override
    public List<LogginInfo> getAll() {
        return entityManager.createQuery("from LogginInfo", LogginInfo.class).getResultList();
    }
}
