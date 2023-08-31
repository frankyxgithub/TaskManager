package com.example.TaskManager.Controller;

import com.example.TaskManager.Model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/tasks/my/")
public class TaskController {
    public TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskInput createTaskInput){
        Task taskCreated = taskService.create(createTaskInput.toTask());
        return new ResponseEntity<>(taskCreated, HttpStatus.CREATED);
    }
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTask(){
        List<Task> tasks = taskService.findAll();

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getById(@PathVariable int id){
        Optional<Task> optionalTask = taskService.findById(id);
        if (optionalTask.isPresent()){
            return new ResponseEntity<>(optionalTask.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> upDateTask(@PathVariable int id, @RequestBody UpdateTaskInput updateTaskInput){
        Optional<Task> optionalTask = taskService.findById(id);
        if (optionalTask.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Task taskToUpdate = optionalTask.get();

        taskToUpdate.setStatus(updateTaskInput.status());
        taskToUpdate.setDueDate(LocalDate.now());

        Task taskUpdated = taskService.update(taskToUpdate);

        return new ResponseEntity<>(taskUpdated, HttpStatus.OK);
    }
    @DeleteMapping("/tasks{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id){
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
