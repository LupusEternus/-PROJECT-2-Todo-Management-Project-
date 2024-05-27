package com.wilk.todo.service;

import com.wilk.todo.dto.ToDoDto;

import java.util.List;
import java.util.Set;

public interface TodoService {

    ToDoDto addToDo(ToDoDto toDoDto);
    ToDoDto addToDoWithUser(ToDoDto toDoDto, Long id);
    ToDoDto getToDoById(Long id);
    List<ToDoDto> getAllToDo();
    ToDoDto updateToDo(ToDoDto toDoDto, Long id);
    void  deleteTodoById(Long id);
    ToDoDto completeToDO(Long id);
    ToDoDto inCompleteToDo(Long id);
    List<ToDoDto> searchToDo(String query);
    Set<ToDoDto> getToDosByUser(Long id);
    Set<ToDoDto> getCompletedToDosByUser(Long id);
    Set<ToDoDto> getIncompleteToDosByUser(Long id);



}
