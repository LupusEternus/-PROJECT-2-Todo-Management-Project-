package com.wilk.todo.repository;

import com.wilk.todo.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    @Query("SELECT t FROM ToDo t WHERE t.title LIKE CONCAT( '%',:query,'%')" +
            " OR t.description LIKE CONCAT('%',:query,'%')")
    List<ToDo> searchToDo (String query);

}
