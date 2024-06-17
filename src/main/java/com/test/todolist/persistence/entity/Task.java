package com.test.todolist.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "task")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean done;

    @Override
    public String toString(){
        return String.format("Task{id=%d, description='%s', createdAt='%s', updatedAt='%s', done='%s'}", id, description, createdAt, updatedAt, done);
    }

}
