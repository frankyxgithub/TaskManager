package com.example.TaskManager.Model;


import java.time.LocalDate;
import java.util.Date;

public record CreateTaskInput(String name, String description, TaskStatusEnum status, LocalDate dueDate){
    public Task toTask(){
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setStatus(status);
        task.setDueDate(dueDate);

        return task;
    }
}
