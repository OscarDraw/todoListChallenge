package com.test.todolist.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    private String confirmPassword;
    private Instant createdAt;
    private Instant updatedAt;

    @Override
    public String toString(){
        return String.format("Task{id=%d, username='%s', createdAt='%s', updatedAt='%s'}", id, username, createdAt, updatedAt);
    }
}
