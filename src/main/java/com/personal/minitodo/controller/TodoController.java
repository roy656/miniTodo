package com.personal.minitodo.controller;

import com.personal.minitodo.model.TodoEntity;
import com.personal.minitodo.model.TodoRequest;
import com.personal.minitodo.model.TodoResponse;
import com.personal.minitodo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/todo")
@Slf4j
@RestController
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    // 파라미터에 필요한 어노테이션 잊지말기
    @PostMapping
    public ResponseEntity<TodoResponse> creatTodo(@RequestBody TodoRequest todoRequest) {
        log.info("Create");
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
        log.info("Read All");
        return ResponseEntity.ok(todoService.readAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOneTodo(@PathVariable Long id) {
        log.info("Read One");
        TodoEntity result = todoService.readOneTodo(id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> updateTodo(@RequestBody TodoRequest todoRequest, @PathVariable Long id) {
        log.info("Update");
        TodoEntity result = todoService.update(todoRequest, id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteOneTodo(@PathVariable Long id) {
        log.info("Delete One");
        todoService.deleteOneTodo(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllTodo() {
        log.info("Delete All");
        todoService.deleteAllTodo();
        return ResponseEntity.ok().build();
    }

}
