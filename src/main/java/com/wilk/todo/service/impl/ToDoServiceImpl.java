package com.wilk.todo.service.impl;

import com.wilk.todo.dto.ToDoDto;
import com.wilk.todo.entity.ToDo;
import com.wilk.todo.repository.ToDoRepository;
import com.wilk.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ToDoServiceImpl implements TodoService {

    private ToDoRepository toDoRepository;

    @Override
    public ToDoDto addToDO(ToDoDto toDoDto) {

        ToDo toDo = new ToDo();
        toDo.setId(toDoDto.getId());
        toDo.setDescription(toDoDto.getDescription());
        toDo.setTitle(toDoDto.getTitle());
        toDo.setCompleted(toDoDto.isCompleted());
        ToDo savedToDO = toDoRepository.save(toDo);

        return new ToDoDto(savedToDO.getId(), savedToDO.getTitle(),savedToDO.getDescription(),savedToDO.isCompleted());

    }
}
