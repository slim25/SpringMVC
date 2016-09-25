package mentorship.program.service.impl;

import mentorship.program.model.User;
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

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
       return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    public List<User> getUser(String name) {
        return userRepository.findByName(name);
    }

    public void editUser(User user) {
        userRepository.save(user);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void removeUser(Long userId) {
        userRepository.delete(userId);
    }


    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> findByLevel(Level level) {
        return userRepository.findByLevel(level);
    }
}
