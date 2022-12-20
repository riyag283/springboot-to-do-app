package com.myApp.todoapp.controller;

import com.myApp.todoapp.entity.ToDo;
import com.myApp.todoapp.exception.ToDoCollectionException;
import com.myApp.todoapp.repository.ToDoRepository;
import com.myApp.todoapp.service.ToDoService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;
    @Autowired
    private ToDoService toDoService;

    @PostMapping
    public ResponseEntity<?> addToDo(@RequestBody ToDo toDo) {
        try {
            ToDo _toDo = toDoService.createToDo(toDo);
            return new ResponseEntity<ToDo>(_toDo, HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (ToDoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getToDos(@RequestParam(value = "id", required = false) Long id) {
        try {
            if (id != null) {
                Optional<ToDo> _todo = toDoRepository.findById(id);
                if(_todo.isPresent()){
                    return new ResponseEntity<>(_todo, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("ToDo not found with id:" + id, HttpStatus.NOT_FOUND);
                }
            }
            List<ToDo> toDos = toDoRepository.findAll();
            if(toDos.size() > 0) {
                return new ResponseEntity<List<ToDo>>(toDos, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("No ToDos available", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateToDo(@RequestParam("id") Long id, @RequestBody ToDo toDo) {
        try {
            Optional<ToDo> toDoOptional = toDoRepository.findById(id);
            if(toDoOptional.isPresent()){
                ToDo toDoToSave = toDoOptional.get();
                toDoToSave.setTask(toDo.getTask() != null ? toDo.getTask() : toDoToSave.getTask());
                toDoToSave.setCompleted(toDo.isCompleted() ? true : toDoToSave.isCompleted());
                toDoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
                toDoToSave.setDeadline(toDo.getDeadline() != null ? toDo.getDeadline() : toDoToSave.getDeadline());
                toDoToSave.setTaskDescription(toDo.getTaskDescription() != null ? toDo.getTaskDescription() :
                        toDoToSave.getTaskDescription());
                toDoRepository.save(toDoToSave);
                return new ResponseEntity<ToDo>(toDoToSave, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("ToDo not found with id:" + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteToDo(@RequestParam("id") Long id) {
        try {
            Optional<ToDo> toDoOptional = toDoRepository.findById(id);
            if(toDoOptional.isPresent()){
                toDoRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("ToDo not found with id:" + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
