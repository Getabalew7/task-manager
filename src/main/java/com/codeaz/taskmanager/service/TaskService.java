package com.codeaz.taskmanager.service;

import com.codeaz.taskmanager.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks = new ArrayList<TaskEntity>();
    long taskId =1;

    public TaskEntity addTask(String title, String description, Date deadline)
    {

        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadline);
        task.setCompleted(false);
        taskId++;
        return task;
    }
    public ArrayList<TaskEntity> getTasks()
    {
        return tasks;
    }
    public TaskEntity getTaskById(long taskId)
    {
        var task = tasks.stream().filter(t->taskId==taskId).findFirst().get();
        return  task;
    }

}
