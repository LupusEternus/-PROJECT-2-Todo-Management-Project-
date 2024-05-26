package com.wilk.todo.service;

import com.wilk.todo.dto.ToDoDto;

import java.util.List;

public interface TodoService {

    ToDoDto addToDo(ToDoDto toDoDto);
    ToDoDto getToDoById(Long id);
    List<ToDoDto> getAllToDo();
    ToDoDto updateToDo(ToDoDto toDoDto, Long id);
    void  deleteTodoById(Long id);
    ToDoDto completeToDO(Long id);
    ToDoDto inCompleteToDo(Long id);
    List<ToDoDto> searchToDo(String query);

}
