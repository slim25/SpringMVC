package mentorship.program.service.impl;

import mentorship.program.dao.impl.UserDaoImpl;
import mentorship.program.model.User;
import mentorship.program.model.UserMentee;
import mentorship.program.model.UserMentor;
import mentorship.program.model.persistance.Level;
import mentorship.program.repository.UserRepository;
import mentorship.program.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Oleksandr_Tertyshnyi on 9/24/2016.
 */
@Service
public class UserServiceImpl implements UserService{

//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private UserDaoImpl userDao;

    @Override
    public List<User> findAll() {
       return userDao.getAll();
    }

    public User getUser(Long id) {
        return userDao.getById(id);
    }

    public List<User> getUser(String name) {
        return userDao.getByName(name);
    }

    public void editUser(User user) {
        userDao.update(user);
    }

    public void addUser(User user) {
        userDao.create(user);
    }

    public void removeUser(Long userId) {
        userDao.deleteById(userId);
    }


    public List<User> findByName(String name) {
        return userDao.getByName(name);
    }

    public List<User> findByLevel(Level level) {
        return userDao.getByLevel(level);
    }

    public List<UserMentor> getMentorsManagingMoreThanTwoMentees(){
        return userDao.getMentorsManagingMoreThanTwoMentees();
    }

    public List<UserMentee> getMenteesWithoutMentorsInLocation(String city){
        return userDao.getMenteesWithoutMentorsInLocation(city);
    }

    public List<Object[]> getMenteesWithMentorshipDurationDESCOrdered(int pageIndex, int noOfRecords) {
        return userDao.getMenteesWithMentorshipDurationDESCOrdered(pageIndex,noOfRecords);
    }

    public List<UserMentor> getAllMentors() {
        return userDao.getAllMentors();
    }

    public List<UserMentee> getAllMentees() {
        return userDao.getAllMentees();
    }
}
