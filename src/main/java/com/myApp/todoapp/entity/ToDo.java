package com.myApp.todoapp.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Transient;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tasks")
public class ToDo {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";
    @Id
    private Long id;
    @NotNull(message = "todo Task cannot be null")
    private String task;
    private String taskDescription;
    private boolean completed;
    private Date createdAt;
    private Date updatedAt;
    private Date deadline;
}
