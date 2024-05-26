package com.wilk.todo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreationDto {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
}
