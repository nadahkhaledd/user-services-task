package com.example.usm.controller;

import com.example.usm.dto.ServiceDTO;
import com.example.usm.entity.Service;
import com.example.usm.exception.DuplicateEntryException;
import com.example.usm.exception.service.MaximumNumberOfServicesReachedException;
import com.example.usm.exception.service.ServiceNotFoundException;
import com.example.usm.exception.user.UserNotFoundException;
import com.example.usm.repository.UserRepository;
import com.example.usm.service.user.IUserService;
import jakarta.validation.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{serialNumber}/services")
public class UserServicesController {

    @Autowired
    private ModelMapper modelMapper;

    private final IUserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserServicesController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping
    public Object find(@PathVariable(value = "serialNumber") String serialNumber){
        return userService.findBySN(serialNumber).getServices().stream()
                .map(service -> modelMapper.map(service, ServiceDTO.class)).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String addService(@PathVariable(value = "serialNumber") String serialNumber, @RequestBody ServiceDTO serviceDTO){
        userService.addUserService(modelMapper.map(serviceDTO, Service.class), serialNumber);
        return "service added";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String return400u(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ServiceNotFoundException.class)
    public String return400s(ServiceNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateEntryException.class)
    public String duplicate(DuplicateEntryException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String constraintViolation(ConstraintViolationException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MaximumNumberOfServicesReachedException.class)
    public String maximumServicesReached(MaximumNumberOfServicesReachedException ex) {
        return ex.getMessage();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String constraint(MethodArgumentNotValidException ex) {
        return "request arguments not sufficient";
    }

}
