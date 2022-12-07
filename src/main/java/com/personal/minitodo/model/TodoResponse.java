package com.personal.minitodo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {

    private Long Id;
    private String title;
    private Long order;
    private boolean completed;
    private String url;


    public TodoResponse(TodoEntity todoentity) {
        this.Id = todoentity.getId();
        this.title = todoentity.getTitle();
        this.order = todoentity.getOrder();
        this.completed = todoentity.isCompleted();

        this.url = "http://localhost:8080/" + this.Id;
    }
}
