package mentorship.program.dao.impl;

import mentorship.program.dao.MentorshipProgramDao;
import mentorship.program.model.MentorshipProgram;
import mentorship.program.model.User;
import mentorship.program.model.UserMentee;
import mentorship.program.model.UserMentor;
import mentorship.program.model.persistance.CityStatistic;
import mentorship.program.model.persistance.Level;
import mentorship.program.model.persistance.UserSuccessCompletions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 10/3/2016.
 */
@Repository
@Transactional
public class MentorshipProgramDaoImpl implements MentorshipProgramDao{

    @PersistenceContext
    private EntityManager entityManager;


    public void create(MentorshipProgram mentorshipProgram) {
        entityManager.persist(mentorshipProgram);
        return;
    }

    public void delete(MentorshipProgram mentorshipProgram) {
        if (entityManager.contains(mentorshipProgram))
            entityManager.remove(mentorshipProgram);
        else
            entityManager.remove(entityManager.merge(mentorshipProgram));
        return;
    }

    public void deleteById(Long mentorshipProgramId){
        entityManager.createNativeQuery("DELETE FROM MentorshipProgram WHERE ID = :mentorshipProgramId")
                .setParameter("mentorshipProgramId", mentorshipProgramId)
                .executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<MentorshipProgram> getAll() {
        return entityManager.createQuery("from MentorshipProgram", MentorshipProgram.class).getResultList();
    }

    public List<MentorshipProgram> getByName(String name) {
        return entityManager.createQuery(
                "FROM MentorshipProgram WHERE name = :name", MentorshipProgram.class)
                .setParameter("name", name)
                .getResultList();
    }

    public MentorshipProgram getById(long id) {
        return entityManager.find(MentorshipProgram.class, id);
    }

    public void update(MentorshipProgram mentorshipProgram) {
        entityManager.merge(mentorshipProgram);
    }


    @Override
    public List<CityStatistic> getCitiesStatistic() {
       List<CityStatistic> cityStatistics = entityManager.createQuery("SELECT " +
               "NEW mentorship.program.model.persistance.CityStatistic(mp.city AS city, COUNT(mp.id) AS amountOfPrograms, " +
               "SUM(pg.attendendingUsers.size) AS peopleInvolved) " +
                "FROM MentorshipProgram mp JOIN mp.programGroups pg GROUP BY mp.city",CityStatistic.class).getResultList();

        return cityStatistics;
    }

    @Override
    public List<UserSuccessCompletions> getStatisticOfSuccessCompletions() {
        List<UserSuccessCompletions> successStatistics =
                entityManager.createQuery("SELECT NEW mentorship.program.model.persistance.UserSuccessCompletions" +
                        "(mentee.id, mentee.name, " +
                        "SUM(CASE WHEN VALUE(mEndDate) < current_date() THEN 1 ELSE 0 END)) " +
                        "FROM UserMentee mentee JOIN mentee.mentorshipEndDate mEndDate GROUP BY mentee.id",UserSuccessCompletions.class).getResultList();


        return successStatistics;
    }
}
