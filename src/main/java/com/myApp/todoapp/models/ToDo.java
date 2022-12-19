package com.myApp.todoapp.models;

public class ToDo {
    public ToDo(Long id, String task, boolean completed) {
        this.id = id;
        this.task = task;
        this.completed = completed;
    }

    private Long id;
    private String task;

    // No-arg constructor; Required by the Java Bean specification,
    // which is used by Spring to create and configure beans.
    public ToDo() {
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

    private boolean completed;

}
