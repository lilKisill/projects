package tracker.service;

import tracker.model.User;

import java.util.List;

public interface UserService {
    User registerNewUser(User user);
    List<User> getAllUsers();
    User findUserByEmail(String email);
    User findUserById(Long id);
    User findUserByUserName(String userName);
    void deleteUser(Long id);
    User findByEmailOrUsername(String email, String userName);
    User saveUser(User user);
}
