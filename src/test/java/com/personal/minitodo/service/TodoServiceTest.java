package com.personal.minitodo.service;

import com.personal.minitodo.model.TodoEntity;
import com.personal.minitodo.model.TodoRequest;
import com.personal.minitodo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;
    @InjectMocks
    private TodoService todoService;


    @Test
    void creatTodo() {
        when(todoRepository.save(any(TodoEntity.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        TodoRequest expected = new TodoRequest();
        expected.setTitle("Test Title");

        TodoEntity actual = todoService.creatTodo(expected);

        assertEquals(expected.getTitle(), actual.getTitle());
    }


    @Test
    void readOneTodo() {

        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setId(120L);
        todoEntity.setTitle("TestTitle");
        todoEntity.setOrder(10L);
        todoEntity.setCompleted(false);
        Optional<TodoEntity> optional = Optional.of(todoEntity);

        given(todoRepository.findById(anyLong()))
                .willReturn(optional);

        TodoEntity actual = todoService.readOneTodo(120L);
        TodoEntity expected = optional.get();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getOrder(), actual.getOrder());
        assertEquals(expected.getCompleted(), actual.getCompleted());
    }

    @Test
    public void readOneTodoFailed() {
        given(todoRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            todoService.readOneTodo(120L);
        });
    }
}