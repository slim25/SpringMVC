package mentorship.program.dao.impl;

import mentorship.program.model.MentorshipProgram;
import mentorship.program.model.User;
import mentorship.program.model.UserMentor;
import mentorship.program.model.persistance.Level;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 10/3/2016.
 */
public class MentorshipGroupDaoImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(MentorshipProgram mentorshipProgram) {
        entityManager.persist(mentorshipProgram);
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

    public List<UserMentor> getMentorsManagingMoreThanTwoMentees(){
        List<UserMentor> mentors = entityManager
                .createQuery("SELECT u FROM User u WHERE COUNT((SELECT mm.mentee_id FROM mentor_mentees mm WHERE mm.mentor_id = u.id)) > 2" , UserMentor.class)
                .getResultList();
        return mentors;
    }



}
