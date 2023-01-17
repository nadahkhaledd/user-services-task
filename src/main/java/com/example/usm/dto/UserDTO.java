package com.example.usm.dto;

import com.example.usm.entity.Service;
import com.example.usm.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserDTO {

    private String serialNumber;
    private String name;
    private String phoneNumber;
    private UserType type;
    private Set<Service> services;
}
