package com.wilk.todo.controller.rest;


import com.wilk.todo.dto.ToDoDto;
import com.wilk.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class ToDoController {

    private TodoService todoService;

    @PostMapping
    public ResponseEntity<ToDoDto> addToDO(@RequestBody ToDoDto toDoDto) {
        ToDoDto savedToDO = todoService.addToDo(toDoDto);
        return new ResponseEntity<>(savedToDO, HttpStatus.CREATED);
    }

    @PostMapping("{id}")
    public ResponseEntity<ToDoDto> addToDoWithUser(@RequestBody ToDoDto toDoDto, @PathVariable Long id){
        return new ResponseEntity<>(todoService.addToDoWithUser(toDoDto,id),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ToDoDto> getToDo(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.getToDoById(id), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<ToDoDto>> getAllToDo() {
        return new ResponseEntity<>(todoService.getAllToDo(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ToDoDto> updateToDo(@RequestBody ToDoDto toDoDto, @PathVariable Long id) {
        return new ResponseEntity<>(todoService.updateToDo(toDoDto, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return new ResponseEntity<>("ToDO deleted Successfully", HttpStatus.OK);
    }

    @PatchMapping("{id}/complete")
    public ResponseEntity<ToDoDto> completeTodo(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.completeToDO(id), HttpStatus.OK);
    }

    @PatchMapping("{id}/in-complete")
    public ResponseEntity<ToDoDto> inCompleteToDo(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.inCompleteToDo(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ToDoDto>> searchToDo(@RequestParam String query) {
        return new ResponseEntity<>(todoService.searchToDo(query), HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<Set<ToDoDto>> getTodosByUser(@PathVariable Long id){
        return new ResponseEntity<>(todoService.getToDosByUser(id),HttpStatus.OK);
    }
    @GetMapping("/user/complete/{id}")
    public ResponseEntity<Set<ToDoDto>> getCompletedToDosByUser(@PathVariable  Long id){
        return new ResponseEntity<>(todoService.getCompletedToDosByUser(id),HttpStatus.OK);

    }
    @GetMapping("/user/in-complete/{id}")
    public ResponseEntity<Set<ToDoDto>> getInCompletedToDosByUser(@PathVariable  Long id){
        return new ResponseEntity<>(todoService.getIncompleteToDosByUser(id),HttpStatus.OK);

    }

}
