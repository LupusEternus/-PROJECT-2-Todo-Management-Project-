package com.wilk.todo.service;

import com.wilk.todo.dto.UserCreationDto;
import com.wilk.todo.dto.UserResponseDto;

public interface UserService {

    UserResponseDto register(UserCreationDto userCreationDto);



}
