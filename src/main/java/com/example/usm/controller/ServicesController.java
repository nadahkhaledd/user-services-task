package com.example.usm.controller;

import com.example.usm.dto.ServiceDTO;
import com.example.usm.entity.Service;
import com.example.usm.enums.ServiceStatus;
import com.example.usm.exception.service.ServiceNotFoundException;
import com.example.usm.service.services.IServicesService;
import com.example.usm.service.user.IUserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServicesController {

    @Autowired
    private ModelMapper modelMapper;

    private final IServicesService servicesService;

    @Autowired
    public ServicesController(IUserService userService, IServicesService servicesService){
        this.servicesService = servicesService;
    }

    @GetMapping
    public List<ServiceDTO> find(){
        return servicesService.getAll().stream()
                .map(service -> modelMapper.map(service, ServiceDTO.class)).toList();
    }

    @GetMapping("/search")
    public List<ServiceDTO> findByVendorAndStatus(@RequestParam(name = "vendor") Optional<String> vendor,
                                                  @RequestParam(name = "status") Optional<ServiceStatus> status){
        if(vendor.isPresent())
            return servicesService.findByVendor(vendor.get()).stream()
                    .map(service -> modelMapper.map(service, ServiceDTO.class)).toList();

        else if(status.isPresent())
            return servicesService.findByStatus(status.get()).stream()
                    .map(service -> modelMapper.map(service, ServiceDTO.class)).toList();

        return servicesService.findByVendorAndStatus(vendor.get(), status.get()).stream()
                .map(service -> modelMapper.map(service, ServiceDTO.class)).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceDTO addService(@Valid @RequestBody ServiceDTO serviceDTO){
        Service service = servicesService.add(modelMapper.map(serviceDTO, Service.class));
        return modelMapper.map(service, ServiceDTO.class);
    }

    @GetMapping("/{uid}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceDTO findByUID(@PathVariable(name = "uid") int uid){
        return modelMapper.map(servicesService.findByUID(uid), ServiceDTO.class);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ServiceNotFoundException.class)
    public String return400s(ServiceNotFoundException ex) {
        return ex.getMessage();

    }






}
