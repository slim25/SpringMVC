package mentorship.program.dao;

import mentorship.program.model.User;
import mentorship.program.model.UserMentee;
import mentorship.program.model.UserMentor;
import mentorship.program.model.persistance.Level;

import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 10/3/2016.
 */
public interface UserDao {

    public void create(User user);

    public void delete(User user);

    public void deleteById(Long userId);

    public List<User> getAll();

    public List<User> getByName(String name);

    public List<User> getByLevel(Level level);

    public User getById(long id);

    public void update(User user);

    public List<UserMentor> getMentorsManagingMoreThanTwoMentees();

    public List<UserMentee> getMenteesWithoutMentorsInLocation(String city);

    public List<Object[]> getMenteesWithMentorshipDurationDESCOrdered(int minIndex, int maxIndex);

    public List<UserMentor> getAllMentors();

    public List<UserMentee> getAllMentees();
}
