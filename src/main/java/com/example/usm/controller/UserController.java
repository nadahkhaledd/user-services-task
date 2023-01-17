package com.example.usm.controller;

import com.example.usm.dto.UserDTO;
import com.example.usm.entity.User;
import com.example.usm.exception.user.UserNotFoundException;
import com.example.usm.service.user.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> findAll(){
        return userService.getAll().stream().map(user -> modelMapper.map(user, UserDTO.class)).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@RequestBody UserDTO userDTO){

        User user = userService.add(modelMapper.map(userDTO, User.class));
        return modelMapper.map(user, UserDTO.class);
    }

    @GetMapping("/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO userBySerialNumber(@PathVariable(name = "serialNumber") String serialNumber){
        return modelMapper.map(userService.findBySN(serialNumber), UserDTO.class);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String return400s(UserNotFoundException ex) {
        return ex.getMessage();

    }


}
