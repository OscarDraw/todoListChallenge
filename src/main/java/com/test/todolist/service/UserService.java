package com.test.todolist.service;

import com.test.todolist.persistence.entity.User;
import com.test.todolist.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public Iterable<User> findAll(){ return userRepository.findAll(); }

    public Optional<User> findById(Long id){ return userRepository.findById(id); }
}
