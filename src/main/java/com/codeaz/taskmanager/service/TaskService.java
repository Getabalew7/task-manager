package com.codeaz.taskmanager.service;

import com.codeaz.taskmanager.dto.ExceptionHandlerDTO;
import com.codeaz.taskmanager.dto.NotesDTO;
import com.codeaz.taskmanager.dto.TaskNotesDTO;
import com.codeaz.taskmanager.entities.NotesEntity;
import com.codeaz.taskmanager.entities.TaskEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private  List<TaskEntity> tasks = new ArrayList<TaskEntity>();
    Long taskId =1L;
    int notesId = 1;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TaskEntity addTask(String title, String description, String deadline) throws ParseException {

        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(dateFormat.parse(deadline));
        task.setCompleted(false);
        taskId++;
        tasks.add(task);
        return task;
    }
    public List<TaskEntity> getTasks(Boolean completed)
    {
        return tasks.stream().filter(t ->t.isCompleted()==completed).collect(Collectors.toList());
    }
    public TaskEntity getTaskById(Long id)  {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow();

    }
    public TaskEntity updateTask(Long id, String description, String deadline, Boolean completed) throws ParseException{
        var task = getTaskById(id);
        System.out.println(completed);
        if(task==null) // check if the task is in the database, if not no need to update anything
            return null;

        if(description != null)
            task.setDescription(description);
        if(deadline != null)
            task.setDeadline(dateFormat.parse(deadline));
        if( completed != null)
            task.setCompleted(completed);

        return task;
    }
    public void deleteTaskById(Long id){
        var task = getTaskById(id);
        tasks.remove(task);
    }
    public List<NotesEntity> getNotes(Long taskId) {
        TaskEntity taskEntity = getTaskById(taskId);
        return taskEntity.getNotes();
    }
    public TaskEntity createNotes(Long taskId, NotesDTO notesDTO) {
        TaskEntity taskEntity = getTaskById(taskId);
        NotesEntity notes = new NotesEntity();
        notes.setBody(notesDTO.getBody());
        notes.setTitle(notesDTO.getTitle());
        notes.setId(notesId++);
        var note = taskEntity.getNotes();
        note.add(notes);
        return taskEntity;
    }
    public TaskEntity updateNote(Long taskId, int notesId, NotesDTO notesDTO) {
        TaskEntity taskEntity = getTaskById(taskId);
        if(taskEntity == null)
            return null;
        List<NotesEntity> notes = taskEntity.getNotes();
        if(notes == null)
            return null;
        var note =  notes.stream()
                .filter(task -> task.getId() ==notesId)
                .findFirst()
                .orElseThrow();

        if(notesDTO.getBody() != null)
            note.setBody(notesDTO.getBody());
        if(notesDTO.getTitle() != null)
            note.setTitle(notesDTO.getTitle());
        return taskEntity;
    }

    public void deleteNotes(Long taskId, int notesId) {
        TaskEntity taskEntity = getTaskById(taskId);
        var notes = taskEntity.getNotes();
        var note = notes.stream().filter(n->n.getId()==notesId).findFirst().orElseThrow();
        notes.remove(note);
    }
}
