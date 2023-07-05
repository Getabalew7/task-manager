package com.codeaz.taskmanager.controller;

import com.codeaz.taskmanager.dto.*;
import com.codeaz.taskmanager.entities.NotesEntity;
import com.codeaz.taskmanager.entities.TaskEntity;
import com.codeaz.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
    public ResponseEntity<TaskEntity> addTask(@RequestBody TaskDTO taskDTO) throws ParseException {
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
    public ResponseEntity<TaskEntity> getTaskByID(@PathVariable Long id){
        var taskEntity = taskService.getTaskById(id);
        return ResponseEntity.ok(taskEntity);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTaskByID(@PathVariable Long id, @RequestBody UpdateTaskDTO dto) throws  ParseException{
        var updateTask = taskService.updateTask(id,dto.getDescription(), dto.getDeadline(), dto.getCompleted());
        return ResponseEntity.ok(updateTask);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskbyId(@PathVariable Long id)
    {
         taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{taskId}/notes")
    public ResponseEntity<TaskNotesDTO> getNotes(@PathVariable Long taskId){
        List<NotesEntity> notes = taskService.getNotes(taskId);
        TaskNotesDTO taskNotes  = new TaskNotesDTO();
        taskNotes.setTaskId(taskId);
        taskNotes.setNotes(notes);
        return ResponseEntity.ok(taskNotes);
    }
    @PostMapping("/{taskId}/notes")
    public ResponseEntity<TaskEntity> createNotes(@PathVariable Long taskId, @RequestBody NotesDTO notesDTO)
    {
        var notes = taskService.createNotes(taskId, notesDTO);
        return ResponseEntity.ok(notes);
    }
    @PutMapping("/{taskId}/notes/{notesId}")
    public ResponseEntity<TaskEntity> updateNote(@PathVariable Long taskId, @PathVariable int notesId, @RequestBody NotesDTO notesDTO){
        TaskEntity notes = taskService.updateNote(taskId, notesId, notesDTO);
        return ResponseEntity.ok(notes);
    }
    @DeleteMapping("/{taskId}/notes/{notesId}")
    public ResponseEntity<Void> deleteNotes(@PathVariable Long taskId, @PathVariable int notesId)
    {
        taskService.deleteNotes(taskId, notesId);
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionHandlerDTO> handleException(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ExceptionHandlerDTO("Invalid Date format"));
        }
        return ResponseEntity.internalServerError().body(new ExceptionHandlerDTO(e.getMessage()));
    }
}
