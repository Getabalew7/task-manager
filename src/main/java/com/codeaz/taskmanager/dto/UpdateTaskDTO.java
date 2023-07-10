package com.codeaz.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateTaskDTO {
    private String description;
    private String  deadline;
    private  Boolean completed;
}
