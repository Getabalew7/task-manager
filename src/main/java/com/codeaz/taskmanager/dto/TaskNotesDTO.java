package com.codeaz.taskmanager.dto;

import com.codeaz.taskmanager.entities.NotesEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TaskNotesDTO {
    private long taskId;
    private List<NotesEntity> notes;
}
