package com.example.usm.controller;

import com.example.usm.dto.ServiceDTO;
import com.example.usm.dto.UserAddService;
import com.example.usm.dto.UserDTO;
import com.example.usm.entity.Service;
import com.example.usm.entity.User;
import com.example.usm.exception.service.ServiceNotFoundException;
import com.example.usm.exception.user.UserNotFoundException;
import com.example.usm.service.services.IServicesService;
import com.example.usm.service.services.ServicesService;
import com.example.usm.service.user.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{serialNumber}/services")
public class UserServicesController {

    @Autowired
    private ModelMapper modelMapper;

    private final IUserService userService;
    private final ServicesService servicesService;

    @Autowired
    public UserServicesController(IUserService userService, ServicesService servicesService){
        this.userService = userService;
        this.servicesService = servicesService;
    }

    @GetMapping
    public Object find(@PathVariable(value = "serialNumber") String serialNumber){
        return userService.findBySN(serialNumber).getServices().stream()
                .map(service -> modelMapper.map(service, ServiceDTO.class)).toList();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String addService(@PathVariable(value = "serialNumber") String serialNumber, @RequestParam(name = "uid") long uid){
        userService.addUserService(servicesService.findByUID(uid), serialNumber);
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



}
