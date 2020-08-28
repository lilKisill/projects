package tracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import tracker.model.Task;
import tracker.model.User;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private String status;
    private UserDto author;

    public Task toUser(){
        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setDate(date);
        task.setStatus(status);
        //task.setAuthor(author);
        return task;
    }

    public static TaskDto fromTask(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setDate(task.getDate());
        taskDto.setStatus(task.getStatus());
        taskDto.setAuthor(UserDto.fromUser(task.getAuthor()));
        return taskDto;
    }
}
