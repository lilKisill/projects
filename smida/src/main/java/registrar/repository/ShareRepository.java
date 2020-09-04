package registrar.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import registrar.model.Share;

public interface ShareRepository extends JpaRepository<Share, Long> {
    Page<Share> findAllByStatus(String status, Pageable pageable);
    Page<Share> findAllByCod(long cod, Pageable pageable);
    Share findByCod(long cod);
}
