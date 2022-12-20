package com.myApp.todoapp.controller;

import com.myApp.todoapp.entity.ToDo;
import com.myApp.todoapp.repository.ToDoRepository;
import com.myApp.todoapp.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.myApp.todoapp.entity.ToDo.SEQUENCE_NAME;

@SpringBootApplication
@RestController
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private SequenceGeneratorService service;

    @PostMapping
    public ToDo addToDo(@RequestBody ToDo toDo) {
        toDo.setId((service.getSequenceNumber(SEQUENCE_NAME)));
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
