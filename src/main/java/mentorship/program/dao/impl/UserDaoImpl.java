package mentorship.program.dao.impl;

import mentorship.program.dao.UserDao;
import mentorship.program.model.*;
import mentorship.program.model.persistance.Level;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 10/3/2016.
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    public void create(User user) {
        entityManager.persist(user);
        return;
    }

    public void delete(User user) {
        if (entityManager.contains(user))
            entityManager.remove(user);
        else
            entityManager.remove(entityManager.merge(user));
        return;
    }

    public void deleteById(Long userId){
     entityManager.remove(getById(userId));
//        entityManager.createQuery("DELETE FROM user u WHERE u.id = :userId")
//            .setParameter("userId", userId)
//            .executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    public List<User> getByName(String name) {
        return entityManager.createQuery(
                "FROM User WHERE name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<User> getByLevel(Level level) {
        return entityManager.createQuery(
                "FROM User WHERE name = :level", User.class)
                .setParameter("level", level)
                .getResultList();
    }

    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public List<UserMentor> getMentorsManagingMoreThanTwoMentees(){
//        List<UserMentor> mentors = entityManager
//                .createQuery("SELECT u FROM UserMentor u WHERE (SELECT COUNT(*) FROM u.mentees ) > 2 " , UserMentor.class)
//                .getResultList();

        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserMentor> cq = qb.createQuery(UserMentor.class);
        Root<UserMentor> root=cq.from(UserMentor.class);

        cq.select(root).where(qb.greaterThan(qb.size(root.<List<UserMentee>>get("mentees")),2) );

        List<UserMentor> mentors = entityManager.createQuery(cq).getResultList();

        return mentors;
    }

    public List<UserMentee> getMenteesWithoutMentorsInLocation(String city){
        List<UserMentee> mentee = entityManager
                .createQuery("SELECT u FROM UserMentee u INNER JOIN u.mentorshipGroup mg " +
                        "WHERE mg.mentorshipProgram.city=:city AND u.userMentor IS NULL )" ,
                UserMentee.class).setParameter("city", city)
                .getResultList();

        return mentee;
    }

    public List<Object[]> getMenteesWithMentorshipDurationDESCOrdered(int pageIndex, int noOfRecords){
        List<Object[]> mentee = entityManager
                .createQuery("SELECT u, SUM( mGr.mentorshipProgram.mentorWeek) FROM UserMentee u " +
                        "INNER JOIN u.mentorshipGroup mGr GROUP BY u.id, u.userMentor, u.mentorshipGroup, u.name, u.birthday, u.lastName, u.email, u.isMentor, u.primarySkill, u.level ORDER BY u.name DESC)" , Object[].class)
                        .setMaxResults(noOfRecords)
                        .setFirstResult((pageIndex - 1)* noOfRecords)
                .getResultList();

        return mentee;
    }

    public List<UserMentee> getMenteesWhoCompletedAllMentorshipPrograms(){
        List<UserMentee> mentors = entityManager
                .createQuery("SELECT u FROM UserMentee u WHERE u.id IN (SELECT med.user_mentee_id FROM mentorship_end_date med WHERE med.end_date IN (SELECT mp.date_of_end FROM mentorship_program mp)))" , UserMentee.class)
                .getResultList();
        return mentors;
    }

    public List<UserMentor> getAllMentors(){
        List<UserMentor> mentors = entityManager
                .createQuery("SELECT u FROM UserMentor u WHERE u.isMentor=true" , UserMentor.class)
                .getResultList();
        return mentors;
    }

    public List<UserMentee> getAllMentees(){
        List<UserMentee> mentees = entityManager
                .createQuery("SELECT u FROM UserMentee u WHERE u.isMentor=false" , UserMentee.class)
                .getResultList();
        return mentees;
    }


}
