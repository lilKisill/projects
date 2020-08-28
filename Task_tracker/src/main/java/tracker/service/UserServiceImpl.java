package tracker.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tracker.model.Role;
import tracker.model.User;
import tracker.repository.RoleRepository;
import tracker.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerNewUser(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        User registeredUser = userRepository.save(user);
        log.info("registerNewUser() function - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = userRepository.findAll();
        log.info("getAllUsers() function - {} users found", result.size());
        return result;
    }

    @Override
    public User findUserByEmail(String email) {
        User result = userRepository.findByEmail(email);
        log.info("findUserByUsername() function - user: {} found by username: {}", result, email);
        return result;
    }

    @Override
    public User findUserById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("findUserById() function - no user found by id: {}", id);
            return null;
        }
        log.info("findUserById() function - user: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        log.info("deleteUser() function - user with id: {} successfully deleted", id);
    }

    @Override
    public User findByEmailOrUsername(String email, String userName) {
        return userRepository.findByEmailOrUsername(email, userName);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
