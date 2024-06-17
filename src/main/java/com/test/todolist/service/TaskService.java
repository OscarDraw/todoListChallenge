package com.test.todolist.service;

import com.test.todolist.persistence.entity.Task;
import com.test.todolist.persistence.entity.User;
import com.test.todolist.persistence.repository.TaskRepository;
import com.test.todolist.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public Iterable<Task> getAll(){ return taskRepository.findAll(); }

    public Iterable<Task> getTasksByUsername(String username) {
        try{
            if (username != null) {
                Optional<User> userOptional = userRepository.findByUsername(username);
                if (userOptional.isEmpty()) throw new RuntimeException("User is null");
                return taskRepository.findByUserId(userOptional.get().getId());
            }else{
                throw new RuntimeException("User is null");
            }
        } catch(Exception e) {
            throw new RuntimeException("User not found" + e);
        }
    }

    public Optional<Task> getById(Long id){ return taskRepository.findById(id); }

    public Task save(Task task, Long userId){
        try{
            if(task.getId() == null){
                task.setCreatedAt(Instant.now());
            }
            task.setUpdatedAt(Instant.now());

            if (userId != null) {
                Optional<User> userOpt = userRepository.findById(userId);
                if(userOpt.isPresent()){
                    task.setUser(userOpt.get());
                }else{
                    throw new RuntimeException("User not exist");
                }
            }else{
                throw new RuntimeException("User not exist");
            }
        }catch(Exception e){
            throw new RuntimeException("ERROR: " + e);
        }

        return taskRepository.save(task);
    }

    public void delete(Task task){
        taskRepository.delete(task);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
