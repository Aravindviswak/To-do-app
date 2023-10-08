package com.ToDoApp.dto;

import java.sql.Date;


import lombok.Data;

@Data
public class TodoDto {

    private String description;
    private Date dueDate;
    private boolean complete;
    private String title;
}
