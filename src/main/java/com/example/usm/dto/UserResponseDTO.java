package com.example.usm.dto;

import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.usm.domain_primitive.PhoneNumber;
import com.example.usm.domain_primitive.SerialNumber;
import com.example.usm.entity.Service;
import com.example.usm.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonSerialize
public class UserResponseDTO {

    private SerialNumber serialNumber;

    private String name;

    private PhoneNumber phoneNumber;

    private UserType type;

    private List<Service> services;
}
