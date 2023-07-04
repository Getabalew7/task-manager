package com.codeaz.taskmanager.service;

import com.codeaz.taskmanager.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks = new ArrayList<TaskEntity>();
    long taskId =1;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TaskEntity addTask(String title, String description, String deadline) throws ParseException {

        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(dateFormat.parse(deadline));
        task.setCompleted(false);
        taskId++;
        return task;
    }
    public ArrayList<TaskEntity> getTasks()
    {
        return tasks;
    }
    public TaskEntity getTaskById(long id)
    {
        var task = tasks.stream().filter(t->taskId==id).findFirst().get();
        return  task;
    }
    public TaskEntity updateTask(Long id, String description, String deadline, Boolean completed) throws ParseException{
        var task = getTaskById(id);
        if(task==null)
            return null;

        if(description != null)
            task.setDescription(description);
        if(deadline != null)
            task.setDeadline(dateFormat.parse(deadline));
        if( completed != null)
            task.setCompleted(completed);

        return task;
    }
}
