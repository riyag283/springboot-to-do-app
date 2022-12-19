package com.myApp.todoapp.controller;

import com.myApp.todoapp.models.ToDo;
import com.myApp.todoapp.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @PostMapping
    public ToDo addToDo(@RequestBody ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    @GetMapping
    public List<ToDo> getToDos() {
        return toDoRepository.findAll();
    }

    @PutMapping("/{id}")
    public ToDo updateToDo(@PathVariable Long id, @RequestBody ToDo toDo) {
        toDo.setId(id);
        return toDoRepository.save(toDo);
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable Long id) {
        toDoRepository.deleteById(id);
    }
}
