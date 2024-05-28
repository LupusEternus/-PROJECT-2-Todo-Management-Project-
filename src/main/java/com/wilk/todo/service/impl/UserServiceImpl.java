package com.wilk.todo.service.impl;

import com.wilk.todo.dto.UserCreationDto;
import com.wilk.todo.dto.UserResponseDto;
import com.wilk.todo.entity.User;
import com.wilk.todo.exceptions.EmailAlreadyTakenException;
import com.wilk.todo.exceptions.UserNotFoundException;
import com.wilk.todo.exceptions.UsernameAlreadyTakenException;
import com.wilk.todo.repository.RoleRepository;
import com.wilk.todo.repository.UserRepository;
import com.wilk.todo.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    @Override
    public UserResponseDto register(UserCreationDto userCreationDto) {
        if (userRepository.existsByEmail(userCreationDto.getEmail())) {
            throw new EmailAlreadyTakenException("Email : " + userCreationDto.getEmail() + " already taken");
        }
        if (userRepository.existsByUsername(userCreationDto.getUsername())) {
            throw new UsernameAlreadyTakenException("Username : "+ userCreationDto.getUsername() + " already taken");
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

    @Override
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " +id));

        return modelMapper.map(user,UserResponseDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " +id));
        userRepository.deleteById(user.getId());
    }

    @Override
    public UserResponseDto updatedUser(UserResponseDto userResponseDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " +id));
        user.setName(userResponseDto.getName());
        userRepository.save(user);
        return modelMapper.map(user,UserResponseDto.class);
    }
}
