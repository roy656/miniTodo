package com.personal.minitodo.controller;

import com.personal.minitodo.model.TodoEntity;
import com.personal.minitodo.model.TodoRequest;
import com.personal.minitodo.model.TodoResponse;
import com.personal.minitodo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/todo")
@RestController
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoResponse> creatTodo(@RequestBody TodoRequest todoRequest) {

        if (ObjectUtils.isEmpty(todoRequest.getTitle()))
            return ResponseEntity.badRequest().build();

        if (ObjectUtils.isEmpty(todoRequest.getOrder()))
            todoRequest.setOrder(0L);   // default 값 설정.

        if (ObjectUtils.isEmpty(todoRequest.getCompleted()))
            todoRequest.setCompleted(false);

        TodoEntity result = todoService.creatTodo(todoRequest);

        return ResponseEntity.ok(new TodoResponse(result));

    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> readAllTodo() {

        return ResponseEntity.ok(todoService.readAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOneTodo(@PathVariable Long id) {
        TodoEntity result = todoService.readOneTodo(id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> updateTodo(@RequestBody TodoRequest todoRequest, @PathVariable Long id) {
        TodoEntity result = todoService.update(todoRequest, id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteOneTodo(@PathVariable Long id) {
        todoService.deleteOneTodo(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllTodo() {
        todoService.deleteAllTodo();
        return ResponseEntity.ok().build();
    }

}
