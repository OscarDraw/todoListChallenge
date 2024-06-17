package com.test.todolist.persistence.repository;

import com.test.todolist.persistence.entity.Task;
import com.test.todolist.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Iterable<Task> findByUserId(Long id);
}
