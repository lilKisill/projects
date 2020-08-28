package tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tracker.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByStatusAndAuthor_Id(String status, Long authorId);
    List<Task> findAllByAuthor_Id(Long id);
    Task findByIdAndAuthor_Id(Long id, Long authorId);
}
