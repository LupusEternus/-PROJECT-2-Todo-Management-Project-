package com.wilk.todo.service.impl;

import com.wilk.todo.dto.UserCreationDto;
import com.wilk.todo.dto.UserResponseDto;
import com.wilk.todo.entity.User;
import com.wilk.todo.exceptions.ResourceNotFoundException;
import com.wilk.todo.repository.RoleRepository;
import com.wilk.todo.repository.UserRepository;
import com.wilk.todo.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    //TODO - custom exceptions for register
    @Override
    public UserResponseDto register(UserCreationDto userCreationDto) {
        if (userRepository.existsByEmail(userCreationDto.getEmail())) {
            throw new RuntimeException("Sorry email already taken");
        }
        if (userRepository.existsByUsername(userCreationDto.getUsername())) {
            throw new RuntimeException("Sorry username already taken");
        }
        User user = new User();
        user.setName(userCreationDto.getName());
        user.setEmail(userCreationDto.getEmail());
        user.setUsername(userCreationDto.getUsername());
        user.setPassword(passwordEncoder.encode(userCreationDto.getPassword()));
        user.setRoles(Collections.singleton(roleRepository.findById(2L).orElseThrow(()->new RuntimeException("something went terrible wrong"))));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserResponseDto.class);

    }
    //TODO custom exception for user not found
    @Override
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        return modelMapper.map(user,UserResponseDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.deleteById(user.getId());
    }

    @Override
    public UserResponseDto updatedUser(UserResponseDto userResponseDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setName(userResponseDto.getName());
        userRepository.save(user);
        return modelMapper.map(user,UserResponseDto.class);
    }
}
