package com.example.usm.dto;

import com.example.usm.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserDTO {

    private String serialNumber;
    private String name;
    private String phoneNumber;
    private UserType type;
}
