package com.personal.minitodo.service;


import com.personal.minitodo.model.TodoEntity;
import com.personal.minitodo.model.TodoRequest;
import com.personal.minitodo.model.TodoResponse;
import com.personal.minitodo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoEntity creatTodo(TodoRequest todoRequest) {

        TodoEntity todoEntity = TodoEntity.builder()
                .title(todoRequest.getTitle())
                .order(todoRequest.getOrder())
                .completed(todoRequest.getCompleted())
                .build();
        return todoRepository.save(todoEntity);

    }

    public List<TodoResponse> readAll() {

        List<TodoEntity> todoList = todoRepository.findAll();
        List<TodoResponse> todoResponseList = new ArrayList<>();

        for (TodoEntity todo : todoList) {
            todoResponseList.add(TodoResponse.builder()
                    .Id(todo.getId())
                    .title(todo.getTitle())
                    .order(todo.getOrder())
                    .completed(todo.getCompleted())
                    .build());
        }
        return todoResponseList;
    }


}
