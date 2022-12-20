package com.myApp.todoapp.service;

import com.myApp.todoapp.entity.ToDo;
import com.myApp.todoapp.exception.ToDoCollectionException;
import jakarta.validation.ConstraintViolationException;

public interface ToDoService {
    public ToDo createToDo(ToDo todo) throws ConstraintViolationException, ToDoCollectionException;
}
