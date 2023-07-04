package com.codeaz.taskmanager.controller;

import com.codeaz.taskmanager.dto.TaskDTO;
import com.codeaz.taskmanager.entities.TaskEntity;
import com.codeaz.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    public TaskController(TaskService taskService)
    {
        this.taskService = taskService;
    }
    @PostMapping("/")
    public ResponseEntity<TaskEntity> addTask(@RequestBody TaskDTO taskDTO)
    {
        var taskEntity =  taskService.addTask(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getDeadline());
        return ResponseEntity.ok(taskEntity);
    }
    @GetMapping("/")
    public ResponseEntity<List<TaskEntity>> getTask()
    {
        var taskEntity = taskService.getTasks();
        return ResponseEntity.ok(taskEntity);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskByID(@PathVariable long taskId){
        var taskEntity = taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskEntity);
    }
}
