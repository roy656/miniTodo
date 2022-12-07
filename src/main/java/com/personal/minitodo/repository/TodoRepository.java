package com.personal.minitodo.repository;

import com.personal.minitodo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    @Override
    Optional<TodoEntity> findById(Long aLong);
}
