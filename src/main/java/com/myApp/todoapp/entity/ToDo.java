package com.myApp.todoapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@Document(collection = "tasks")
public class ToDo {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";
    @Id
    private Long id;
    @Column(nullable = false)
    private String task;
    @Column(columnDefinition = "boolean default true")
    private boolean completed;

    // No-arg constructor; Required by the Java Bean specification,
    // which is used by Spring to create and configure beans.
    public ToDo() {
    }

    public ToDo(Long id, String task, boolean completed) {
        this.id = id;
        this.task = task;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
