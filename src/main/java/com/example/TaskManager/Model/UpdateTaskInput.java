package com.example.TaskManager.Model;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;


public record UpdateTaskInput(TaskStatusEnum status, LocalDate dueDate){

}
