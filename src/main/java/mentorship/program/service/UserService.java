package mentorship.program.service;

import mentorship.program.model.User;
import mentorship.program.model.UserMentee;
import mentorship.program.model.UserMentor;
import mentorship.program.model.persistance.Level;

import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 9/22/2016.
 */
public interface UserService {
    public User getUser(Long id);

    public List<User> getUser(String name);

    public void addUser(User user);

    public void removeUser(Long userId);

    public void editUser(User user);

    public List<User> findByName(String name);

    public List<User> findByLevel(Level level);

    public List<User> findAll();

    public List<UserMentor> getMentorsManagingMoreThanTwoMentees();

    public List<UserMentee> getMenteesWithoutMentorsInLocation(String city);

    public List<Object[]> getMenteesWithMentorshipDurationDESCOrdered(int pageIndex, int noOfRecords);

}
