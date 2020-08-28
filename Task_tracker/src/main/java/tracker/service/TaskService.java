package tracker.service;

import tracker.model.Task;

import java.util.List;

public interface TaskService {
    Task save(Task task);
    List<Task> getAllTasks();
    List<Task> getAllTasksFromUser(Long id);
    Task findTaskByIdAndAuthor(Long id, Long authorId);
    void deleteTask(Long id);
    List<Task> findAllByStatus(String status, Long authorId);
    Task findTaskById(Long id);
}
