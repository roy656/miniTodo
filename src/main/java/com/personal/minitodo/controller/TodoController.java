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
        System.out.println("created");

        if (ObjectUtils.isEmpty(todoRequest.getTitle()))
            return ResponseEntity.badRequest().build();

        if (ObjectUtils.isEmpty(todoRequest.getOrder()))
            todoRequest.setOrder(0L);

        if (ObjectUtils.isEmpty(todoRequest.getCompleted()))
            todoRequest.setCompleted(false);

        TodoEntity result = todoService.creatTodo(todoRequest);

        return ResponseEntity.ok(new TodoResponse(result));

    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> readAllTodo() {
        System.out.println("read all");

        return ResponseEntity.ok(todoService.readAll());
    }
//
//    @GetMapping("{Id}")
//    public ResponseEntity<TodoResponse> readOneTodo(TodoService todoService) {
//        System.out.println("read one");
//    }
//
//    @PatchMapping("{Id}")
//    public ResponseEntity<TodoResponse> updateTodo(TodoService todoService) {
//        System.out.println("updated");
//    }
//
//    @DeleteMapping("{Id}")
//    public ResponseEntity<Object> deleteOneTodo(TodoService todoService) {
//        System.out.println("deleted one");
//    }
//
//    @DeleteMapping
//    public ResponseEntity<Object> deleteAllTodo(TodoService todoService) {
//        System.out.println("deleted all");
//    }

}
