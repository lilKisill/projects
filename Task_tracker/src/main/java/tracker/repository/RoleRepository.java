package tracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tracker.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
