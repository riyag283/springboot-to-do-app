package com.myApp.todoapp.exception;

public class ToDoCollectionException extends Exception {

    private static final long serialVersionUID = 1L;

    public ToDoCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "ToDo with id:" + id + " not found!";
    }

    public static String ToDoAlreadyExists() {
        return "ToDo with given Task Name already exists!";
    }
}
