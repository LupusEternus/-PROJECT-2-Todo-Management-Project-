package com.wilk.todo.controller;


import com.wilk.todo.dto.ToDoDto;
import com.wilk.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class ToDoController {

    private TodoService todoService;

    @PostMapping
    public ResponseEntity<ToDoDto> addToDO(@RequestBody ToDoDto toDoDto){
        ToDoDto savedToDO = todoService.addToDo(toDoDto);
        return new ResponseEntity<>(savedToDO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public  ResponseEntity<ToDoDto> getToDo(@PathVariable Long id){
        return new ResponseEntity<>(todoService.getToDoById(id),HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<ToDoDto>> getAllToDo(){
        return new ResponseEntity<>(todoService.getAllToDo(),HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<ToDoDto> updateToDo(@RequestBody ToDoDto toDoDto, @PathVariable Long id){
        return new ResponseEntity<>(todoService.updateToDo(toDoDto,id),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodoById(id);
        return new ResponseEntity<>("ToDO deleted Successfully",HttpStatus.OK);
    }
    @PatchMapping("{id}")
    public ResponseEntity<ToDoDto> completeTodo(@PathVariable Long id){
        return new ResponseEntity<>(todoService.completeToDO(id),HttpStatus.OK);
    }
}
