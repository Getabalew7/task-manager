package com.codeaz.taskmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private String deadline;
}
