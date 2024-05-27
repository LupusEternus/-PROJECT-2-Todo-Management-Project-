package com.wilk.todo.service;

import com.wilk.todo.dto.UserCreationDto;
import com.wilk.todo.dto.UserResponseDto;

public interface UserService {

    UserResponseDto register(UserCreationDto userCreationDto);

    UserResponseDto getUser(Long id);

    void deleteUser(Long id);

    UserResponseDto updatedUser(UserResponseDto userResponseDto, Long id);


}
