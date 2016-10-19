package mentorship.program.dao.impl;

import mentorship.program.model.MentorshipGroup;
import mentorship.program.model.MentorshipProgram;
import mentorship.program.model.User;
import mentorship.program.model.UserMentor;
import mentorship.program.model.persistance.Level;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 10/3/2016.
 */
@Repository
@Transactional
public class MentorshipGroupDaoImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(MentorshipGroup mentorshipGroup) {
        entityManager.persist(mentorshipGroup);
    }

    public void delete(MentorshipGroup mentorshipGroup) {
        if (entityManager.contains(mentorshipGroup))
            entityManager.remove(mentorshipGroup);
        else
            entityManager.remove(entityManager.merge(mentorshipGroup));
        return;
    }

    public void deleteById(Long mentorshipGroupId){
        entityManager.createNativeQuery("DELETE FROM mentorship_group mg WHERE mg.id = :mentorshipGroupId")
                .setParameter("mentorshipGroupId", mentorshipGroupId)
                .executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<MentorshipGroup> getAll() {
        return entityManager.createQuery("from MentorshipGroup", MentorshipGroup.class).getResultList();
    }

    public List<MentorshipGroup> getByName(String name) {
        return entityManager.createQuery(
                "FROM MentorshipGroup WHERE name = :name", MentorshipGroup.class)
                .setParameter("name", name)
                .getResultList();
    }

    public MentorshipGroup getById(long id) {
        return entityManager.find(MentorshipGroup.class, id);
    }

    public void update(MentorshipGroup mentorshipGroup) {
        entityManager.merge(mentorshipGroup);
    }

}
