package com.myApp.todoapp.service;

import com.myApp.todoapp.entity.ToDo;
import com.myApp.todoapp.exception.ToDoCollectionException;
import com.myApp.todoapp.repository.ToDoRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static com.myApp.todoapp.entity.ToDo.SEQUENCE_NAME;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private SequenceGeneratorService service;

    public ToDo createToDo(ToDo toDo) throws ConstraintViolationException, ToDoCollectionException{
        Optional<ToDo> toDoOptional = toDoRepository.findByTask(toDo.getTask());
        if (toDoOptional.isPresent()) {
            throw new ToDoCollectionException(ToDoCollectionException.ToDoAlreadyExists());
        }
        else {
            toDo.setId((service.getSequenceNumber(SEQUENCE_NAME)));
            toDo.setCreatedAt(new Date(System.currentTimeMillis()));
            toDo.setUpdatedAt(new Date(System.currentTimeMillis()));
            toDoRepository.save(toDo);
        }
        return toDo;
    }

}
