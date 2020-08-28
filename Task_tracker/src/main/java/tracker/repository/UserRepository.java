package tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tracker.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String userName);
    User findByEmailOrUsername(String email, String userName);
}
