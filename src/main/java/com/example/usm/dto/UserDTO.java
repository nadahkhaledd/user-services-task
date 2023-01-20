package com.example.usm.dto;

import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.usm.domain_primitive.PhoneNumber;
import com.example.usm.domain_primitive.SerialNumber;
import com.example.usm.entity.Service;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Setter @Getter
@EqualsAndHashCode
@JsonSerialize
public class UserDTO {

    @NotNull
    private SerialNumber serialNumber;

    @NotNull
    private String name;

    @NotNull
    private PhoneNumber phoneNumber;

    @Size(min = 0, max = 10)
    private List<Service> services;

    public UserDTO(){
        this.services = new ArrayList<>();
    }

}
