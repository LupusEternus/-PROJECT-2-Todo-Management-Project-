package com.wilk.todo.repository;

import com.wilk.todo.dto.ToDoDto;
import com.wilk.todo.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {


}
