package com.wilk.todo.controller.rest;


import com.wilk.todo.dto.UserCreationDto;
import com.wilk.todo.dto.UserResponseDto;
import com.wilk.todo.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserCreationDto userCreationDto) {
        return new ResponseEntity<>(userService.register(userCreationDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserResponseDto userResponseDto, @PathVariable Long id) {
        return new ResponseEntity<>(userService.updatedUser(userResponseDto, id), HttpStatus.OK);
    }


}
