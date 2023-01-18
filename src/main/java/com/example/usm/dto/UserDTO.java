package com.example.usm.dto;

import com.example.usm.entity.Service;
import com.example.usm.enums.UserType;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Setter @Getter
public class UserDTO {

    private String serialNumber;
    private String name;
    private String phoneNumber;
    private UserType type;
    private Set<Service> services;

    public UserDTO (){
        this.services = new HashSet<>();
    }
}
