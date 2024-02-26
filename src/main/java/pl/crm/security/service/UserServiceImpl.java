package pl.crm.security.service;

import pl.crm.model.users.User;
import pl.crm.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String user) {
        return userRepository.findByUsername(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
