package com.codeaz.taskmanager.entities;


import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TaskEntity {
    @Generated
    private Long id;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;
    private List<NotesEntity> notes = new ArrayList<>();
}
