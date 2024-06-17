package com.test.todolist.service;

import com.test.todolist.persistence.entity.Task;
import com.test.todolist.persistence.entity.User;
import com.test.todolist.persistence.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Iterable<Task> getAll(){ return taskRepository.findAll(); }

    public Optional<Task> getById(Long id){ return taskRepository.findById(id); }

    public Task save(Task task){
        if(task.getId() == null){
            task.setCreatedAt(Instant.now());
        }

        task.setUpdatedAt(Instant.now());
        return taskRepository.save(task);
    }

    public void delete(Task task){
        taskRepository.delete(task);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
