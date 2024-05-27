package com.wilk.todo.service.impl;

import com.wilk.todo.dto.ToDoDto;
import com.wilk.todo.entity.ToDo;
import com.wilk.todo.entity.User;
import com.wilk.todo.exceptions.ResourceNotFoundException;
import com.wilk.todo.repository.ToDoRepository;
import com.wilk.todo.repository.UserRepository;
import com.wilk.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToDoServiceImpl implements TodoService {

    private UserRepository userRepository;
    private ToDoRepository toDoRepository;
    private ModelMapper modelMapper;

    @Override
    public ToDoDto addToDo(ToDoDto toDoDto) {
        ToDo savedToDO = toDoRepository.save(modelMapper.map(toDoDto,ToDo.class));
        return modelMapper.map(savedToDO,ToDoDto.class);
    }

    @Override
    public ToDoDto addToDoWithUser(ToDoDto toDoDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with ID: " + id));
        ToDo toDo = modelMapper.map(toDoDto,ToDo.class);
        toDo.setUser(user);
        ToDo savedToDo = toDoRepository.save(toDo);
        return modelMapper.map(savedToDo,ToDoDto.class);

    }

    @Override
    public ToDoDto getToDoById(Long id) {
        ToDo toDo = toDoRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("ToDo not found"));
        return modelMapper.map(toDo,ToDoDto.class);
    }

    @Override
    public List<ToDoDto> getAllToDo() {
        return  toDoRepository.findAll().stream().map((t)-> modelMapper.map(t,ToDoDto.class)).toList();
    }

    @Override
    public ToDoDto updateToDo(ToDoDto toDoDto, Long id) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if(optionalToDo.isPresent()){
            ToDo foundToDO = optionalToDo.get();
            foundToDO.setTitle(toDoDto.getTitle());
            foundToDO.setDescription(toDoDto.getDescription());
            foundToDO.setCompleted(toDoDto.isCompleted());
            return modelMapper.map(toDoRepository.save(foundToDO),ToDoDto.class);
        }else {
            throw new ResourceNotFoundException("Todo not found");
        }
    }

    @Override
    public void deleteTodoById(Long id) {
        ToDo toDooDelete = toDoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        toDoRepository.deleteById(toDooDelete.getId());
    }

    @Override
    public ToDoDto completeToDO(Long id) {
        ToDo toDoo = toDoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id ));
        toDoo.setCompleted(Boolean.TRUE);
        ToDo savedToDO = toDoRepository.save(toDoo);
        return modelMapper.map(savedToDO,ToDoDto.class);

    }
    @Override
    public ToDoDto inCompleteToDo(Long id) {
        ToDo toDoo = toDoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id ));
        toDoo.setCompleted(Boolean.FALSE);
        ToDo savedToDO = toDoRepository.save(toDoo);
        return modelMapper.map(savedToDO,ToDoDto.class);
    }

    @Override
    public List<ToDoDto> searchToDo(String query) {
        List<ToDo> toDoList = toDoRepository.searchToDo(query);
        return toDoList.stream().map(t -> modelMapper.map(t,ToDoDto.class)).toList();
    }
    @Override
    public Set<ToDoDto> getToDosByUser(Long id) {
        Set<ToDo> toDoSet = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found")).getToDos();
        return toDoSet.stream().map(todo-> modelMapper.map(todo,ToDoDto.class)).collect(Collectors.toSet());

    }
    @Override
    public Set<ToDoDto> getCompletedToDosByUser(Long id) {
        Set<ToDo> toDoSet = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found"))
                .getToDos().stream()
                .filter(ToDo::isCompleted)
                .collect(Collectors.toSet());
        return toDoSet.stream().map(t -> modelMapper.map(t,ToDoDto.class)).collect(Collectors.toSet());
    }
    @Override
    public Set<ToDoDto> getIncompleteToDosByUser(Long id) {
        Set<ToDo> toDoSet = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found"))
                .getToDos().stream()
                .filter(toDo -> !toDo.isCompleted() )
                .collect(Collectors.toSet());
        return toDoSet.stream().map(t -> modelMapper.map(t,ToDoDto.class)).collect(Collectors.toSet());
    }
}
