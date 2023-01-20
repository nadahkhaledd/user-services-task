package com.example.usm.dto;

import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.usm.domain_primitive.PhoneNumber;
import com.example.usm.domain_primitive.SerialNumber;
import com.example.usm.entity.Service;
import com.example.usm.enums.UserType;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@JsonSerialize
public class UserResponseDTO {

    private SerialNumber serialNumber;

    private String name;

    private PhoneNumber phoneNumber;

    private UserType type;

    private List<Service> services;
}
