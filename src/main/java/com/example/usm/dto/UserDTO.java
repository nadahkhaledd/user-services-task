package com.example.usm.dto;

import com.example.usm.domainPrimitive.PhoneNumber;
import com.example.usm.domainPrimitive.SerialNumber;
import com.example.usm.entity.Service;
import com.example.usm.enums.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Setter @Getter
public class UserDTO {

    @NotNull
    private SerialNumber serialNumber;

    @NotNull
    private String name;

    @NotNull
    private PhoneNumber phoneNumber;

    @NotNull
    private UserType type;

    @Size(min = 0, max = 10)
    private List<Service> services;

    public UserDTO (){
        this.services = new ArrayList<>();
    }



}
