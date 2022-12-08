package com.personal.minitodo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.minitodo.model.TodoEntity;
import com.personal.minitodo.model.TodoRequest;
import com.personal.minitodo.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    TodoService todoService;

    private TodoEntity expected;

    @BeforeEach
    void setUp() {
        this.expected = new TodoEntity();
        this.expected.setId(120L);
        this.expected.setTitle("TestTitle");
        this.expected.setOrder(0L);
        this.expected.setCompleted(false);
    }

    @Test
    void creatTodo() throws Exception {
        when(todoService.creatTodo(any(TodoRequest.class)))
                .then((i) -> {
                    TodoRequest todoRequest = i.getArgument(0, TodoRequest.class);

                    return new TodoEntity(expected.getId(),
                            todoRequest.getTitle(),
                            expected.getOrder(),
                            expected.getCompleted());
                });

        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setTitle("MY TITLE");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(todoRequest);

        this.mvc.perform(post("/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("MY TITLE"));
    }

    @Test
    void readOneTodo() {
    }
}