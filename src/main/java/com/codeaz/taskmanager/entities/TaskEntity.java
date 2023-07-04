package com.codeaz.taskmanager.entities;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TaskEntity {

    @Generated
    private Long id;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;
}
