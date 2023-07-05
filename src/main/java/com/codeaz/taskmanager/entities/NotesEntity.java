package com.codeaz.taskmanager.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Data
public class NotesEntity {
    private int id;
    private String title;
    private String body;
}
