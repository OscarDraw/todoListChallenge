package com.test.todolist.controller;

import com.test.todolist.persistence.entity.Task;
import com.test.todolist.service.TaskService;
import com.test.todolist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;


@Slf4j
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @RequestMapping(value="/create-task", method = RequestMethod.POST)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        try {
            return ResponseEntity.ok(taskService.save(task));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value="/update-task", method = RequestMethod.POST)
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        try {
            Optional<Task> existingTaskOpt = taskService.getById(task.getId());
            if (existingTaskOpt.isPresent()) {
                Task existingTask = existingTaskOpt.get();
                existingTask.setTitle(task.getTitle());
                existingTask.setDescription(task.getDescription());
                existingTask.setDone(task.getDone());
                existingTask.setUpdatedAt(Instant.now());
                return ResponseEntity.ok(taskService.save(existingTask));
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
