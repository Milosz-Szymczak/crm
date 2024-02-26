package pl.crm.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.crm.model.users.User;

import java.util.List;

@Service
public interface UserService {


    void saveUser(User user);

    List<User> getUsers();

    User findByUsername(String user);

    List<User> getAllUsers();
}
