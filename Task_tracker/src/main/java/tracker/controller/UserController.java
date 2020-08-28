package tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tracker.dto.TaskDto;
import tracker.dto.UserDto;
import tracker.model.Task;
import tracker.model.User;
import tracker.service.TaskService;
import tracker.service.UserService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/home/user")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TaskService taskService;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder, TaskService taskService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.taskService = taskService;
    }


    @GetMapping("/get")
    public ResponseEntity<UserDto> getUser(Principal principal) {
        User user = userService.findUserByUserName(principal.getName());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/put")
    public ResponseEntity<UserDto> changeUser(Principal principal, @RequestBody User user) {
        User changeUser = userService.findUserByUserName(principal.getName());
        if (changeUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        changeUser.setFirstName(user.getFirstName());
        changeUser.setLastName(user.getLastName());
        changeUser.setUsername(user.getUsername());
        changeUser.setEmail(user.getEmail());
        changeUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(changeUser);
        UserDto result = UserDto.fromUser(changeUser);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteTask(Principal principal) {
        try {
            User deleteUser = userService.findUserByUserName(principal.getName());
            List<Task> listOfTasks = new ArrayList<Task>(taskService.getAllTasksFromUser(deleteUser.getId()));
            if (!listOfTasks.isEmpty()) {
                for (Task task : listOfTasks) {
                    taskService.deleteTask(task.getId());
                }
            }
            userService.deleteUser(deleteUser.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/createTask")
    public ResponseEntity<TaskDto> createTask(Principal principal, @RequestBody Task taskRequest) {
        try {
            if (taskRequest == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Task task = new Task();
            task.setTitle(taskRequest.getTitle());
            task.setDescription(taskRequest.getDescription());
            task.setStatus("View");
            task.setDate(LocalDate.now());
            User user = userService.findUserByUserName(principal.getName());
            task.setAuthor(user);
            taskService.save(task);
            TaskDto result = TaskDto.fromTask(task);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasksFromUser(Principal principal) {
        try {
            User user = userService.findUserByUserName(principal.getName());
            List<Task> listOfTasks = new ArrayList<Task>(taskService.getAllTasksFromUser(user.getId()));
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

    @GetMapping("/{status}")
    public ResponseEntity<List<TaskDto>> getAllTasksWithStatus(Principal principal, @PathVariable String status) {
        try {
            User user = userService.findUserByUserName(principal.getName());
            List<Task> listOfTasksWithStatus = new ArrayList<Task>(taskService.findAllByStatus(status, user.getId()));
            if (listOfTasksWithStatus.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<TaskDto> result = new ArrayList<>();
            for (int i = 0; i < listOfTasksWithStatus.size(); i++) {
                result.add(TaskDto.fromTask(listOfTasksWithStatus.get(i)));
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> getAllTasksFromUser(Principal principal, @PathVariable("id") Long id) {
        User user = userService.findUserByUserName(principal.getName());
        Task task = taskService.findTaskByIdAndAuthor(id, user.getId());
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TaskDto result = TaskDto.fromTask(task);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> updateTask(Principal principal, @PathVariable("id") Long id, @RequestBody Task task) {
        User user = userService.findUserByUserName(principal.getName());
        Task changeTask = taskService.findTaskByIdAndAuthor(id, user.getId());
        if (changeTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        changeTask.setTitle(task.getTitle());
        changeTask.setDescription(task.getDescription());
        changeTask.setStatus(task.getStatus());
        changeTask.setDate(task.getDate());
        changeTask.setAuthor(task.getAuthor());
        taskService.save(changeTask);
        TaskDto result = TaskDto.fromTask(changeTask);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/tasks/status/{id}")
    public ResponseEntity<TaskDto> changeStatusTask(Principal principal, @PathVariable("id") Long id, @RequestBody Task task) {
        User user = userService.findUserByUserName(principal.getName());
        Task changeTask = taskService.findTaskByIdAndAuthor(id, user.getId());
        if (changeTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        changeTask.setStatus(task.getStatus());
        taskService.save(changeTask);
        TaskDto result = TaskDto.fromTask(changeTask);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/tasks/author/{id}")
    public ResponseEntity<TaskDto> changeAuthorTask(Principal principal, @PathVariable("id") Long id, @RequestBody Task task) {
        User user = userService.findUserByUserName(principal.getName());
        Task changeTask = taskService.findTaskByIdAndAuthor(id, user.getId());
        if (changeTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        changeTask.setAuthor(task.getAuthor());
        taskService.save(changeTask);
        TaskDto result = TaskDto.fromTask(changeTask);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> deleteTask(Principal principal, @PathVariable("id") Long id) {
        try {
            User user = userService.findUserByUserName(principal.getName());
            Task deleteTask = taskService.findTaskByIdAndAuthor(id, user.getId());
            if (deleteTask == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            taskService.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
