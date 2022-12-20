package com.myApp.todoapp.repository;

import com.myApp.todoapp.entity.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo, Long> {
}
