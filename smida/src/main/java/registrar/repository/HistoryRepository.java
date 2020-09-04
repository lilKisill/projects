package registrar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import registrar.model.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
