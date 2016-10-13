package mentorship.program.dao;

import mentorship.program.model.MentorshipProgram;
import mentorship.program.model.User;
import mentorship.program.model.UserMentor;
import mentorship.program.model.persistance.CityStatistic;
import mentorship.program.model.persistance.Level;
import mentorship.program.model.persistance.UserSuccessCompletions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 10/3/2016.
 */
public interface MentorshipProgramDao {

    public List<CityStatistic> getCitiesStatistic();

    public List<UserSuccessCompletions> getStatisticOfSuccessCompletions();

}
