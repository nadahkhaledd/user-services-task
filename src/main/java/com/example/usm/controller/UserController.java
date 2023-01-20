package com.example.usm.controller;

import com.example.usm.dto.UserDTO;
import com.example.usm.entity.User;
import com.example.usm.enums.UserType;
import com.example.usm.exception.user.UserNotFoundException;
import com.example.usm.mapping.UserMapping;
import com.example.usm.service.user.IUserService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;
    private UserMapping userMapping = new UserMapping();

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping
    public Object find(@RequestParam(name = "phoneNumber", required = false) Optional<String> phoneNumber,
                              @RequestParam(name = "type", required = false) Optional<UserType> type){
        if(phoneNumber.isPresent())
            return userMapping.mapToDTO(userService.findByPhone(phoneNumber.get()));

        return type.map(userType -> userService.findByType(userType).stream().map(user -> userMapping.mapToDTO(user)).toList()).orElseGet(() -> userService.getAll().stream().map(user -> userMapping.mapToDTO(user)).toList());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@Valid @RequestBody UserDTO userDTO){
        User mapped = userMapping.mapToEntity(userDTO);
        User user = userService.add(mapped);
        return userMapping.mapToDTO(user);
    }

    @GetMapping("/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO userBySerialNumber(@PathVariable(name = "serialNumber") String serialNumber){
        return userMapping.mapToDTO(userService.findBySN(serialNumber));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String return400s(UserNotFoundException ex) {
        return ex.getMessage();

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String domainPrimitiveError(HttpMessageNotReadableException ex) {
        return ex.getMessage().split("problem: ")[1];
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String constraintViolation(ConstraintViolationException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String constraint(MethodArgumentNotValidException ex) {
        return "request arguments not sufficient";
    }


}
