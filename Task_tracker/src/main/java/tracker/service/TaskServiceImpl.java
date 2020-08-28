package tracker.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tracker.model.Task;
import tracker.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        Task createTask = taskRepository.save(task);
        log.info("save() function - task: {} successfully registered", createTask);
        return createTask;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> result = taskRepository.findAll();
        log.info("getAllTasks() function - {} tasks found", result.size());
        return result;
    }

    @Override
    public List<Task> getAllTasksFromUser(Long id) {
        List<Task> result = taskRepository.findAllByAuthor_Id(id);
        log.info("getAllTasksFromUser() function - {} tasks found", result.size());
        return result;
    }

    @Override
    public Task findTaskByIdAndAuthor(Long id, Long authorId) {
        Task getTask = taskRepository.findByIdAndAuthor_Id(id, authorId);
        log.info("findTaskByIdAndAuthor() function - task: {} successfully found", getTask);
        return getTask;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
        log.info("deleteTask() function - task with id: {} successfully deleted", id);
    }

    @Override
    public List<Task> findAllByStatus(String status, Long authorId) {
        List<Task> result = taskRepository.findAllByStatusAndAuthor_Id(status, authorId);
        log.info("findAllByStatus() function - {} tasks found", result.size());
        return result;
    }

    @Override
    public Task findTaskById(Long id) {
        Task result = taskRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("findTaskById() function - no task found by id: {}", id);
            return null;
        }
        log.info("findTaskById() function - task: {} found by id: {}", result, id);
        return result;
    }

}
