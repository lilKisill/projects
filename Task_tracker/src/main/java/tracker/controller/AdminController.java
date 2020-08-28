package tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tracker.dto.TaskDto;
import tracker.dto.UserDto;
import tracker.model.Task;
import tracker.model.User;
import tracker.service.TaskService;
import tracker.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/home/admin")
@CrossOrigin(origins = "http://localhost:8080")
public class AdminController {
    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public AdminController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        try {
            List<User> listOfUsers = new ArrayList<User>(userService.getAllUsers());
            if (listOfUsers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<UserDto> result = new ArrayList<>();
            for (int i = 0; i < listOfUsers.size(); i++) {
                result.add(UserDto.fromUser(listOfUsers.get(i)));
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        try { ;
            List<Task> listOfTasks = new ArrayList<Task>(taskService.getAllTasks());
            if (listOfTasks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<TaskDto> result = new ArrayList<>();
            for (int i = 0; i < listOfTasks.size(); i++) {
                result.add(TaskDto.fromTask(listOfTasks.get(i)));
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> getAllTasksFromUser(@PathVariable("id") Long id) {
        Task task = taskService.findTaskById(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TaskDto result = TaskDto.fromTask(task);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
