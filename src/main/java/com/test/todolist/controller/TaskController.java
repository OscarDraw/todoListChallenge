package com.test.todolist.controller;

import com.test.todolist.DTO.TaskDTO;
import com.test.todolist.persistence.entity.Task;
import com.test.todolist.persistence.entity.User;
import com.test.todolist.service.TaskService;
import com.test.todolist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @RequestMapping(value="/get-task/{username}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Task> getTaskByUsername(@PathVariable String username) {
        try {
            return taskService.getTasksByUsername(username);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @RequestMapping(value="/get-all-task", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Task> getTask() {
        try {
            return taskService.getAll();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @RequestMapping(value="/create-task", method = RequestMethod.POST)
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) {
        try {
            Task task = new Task();
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setDone(taskDTO.getDone());

            return ResponseEntity.ok(taskService.save(task, taskDTO.getUserId()));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value="/update-task", method = RequestMethod.POST)
    public ResponseEntity<Task> updateTask(@RequestBody TaskDTO taskDTO) {
        try {
            Optional<Task> existingTaskOpt = taskService.getById(taskDTO.getId());
            if (existingTaskOpt.isPresent()) {
                Task existingTask = existingTaskOpt.get();
                existingTask.setTitle(taskDTO.getTitle());
                existingTask.setDescription(taskDTO.getDescription());
                existingTask.setDone(taskDTO.getDone());
                existingTask.setUpdatedAt(Instant.now());

                if (taskDTO.getUserId() != null) {
                    Optional<User> userOpt = userService.findById(taskDTO.getUserId());
                    userOpt.ifPresent(existingTask::setUser);
                }

                Task savedTask = taskService.save(existingTask, taskDTO.getUserId());
                return ResponseEntity.ok(savedTask);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
