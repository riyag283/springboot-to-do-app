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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateToDo(@PathVariable Long id, @RequestBody ToDo toDo) {
        try {
            toDo.setId(id);
            ToDo _toDo = toDoRepository.save(toDo);
            return new ResponseEntity<ToDo>(_toDo, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteToDo(@PathVariable Long id) {
        try {
            toDoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
