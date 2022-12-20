package com.myApp.todoapp.repository;

import com.myApp.todoapp.entity.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo, Long> {

    @Query("{'task': ?0}")
    Optional<ToDo>findByTask(String task);
}
