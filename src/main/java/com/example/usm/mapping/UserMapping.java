package com.example.usm.mapping;

import com.example.usm.domain_primitive.PhoneNumber;
import com.example.usm.domain_primitive.SerialNumber;
import com.example.usm.dto.UserDTO;
import com.example.usm.entity.User;

public class UserMapping {

    public User mapToEntity(UserDTO userDTO){
        return new User(userDTO.getSerialNumber().value(),
                userDTO.getName(), userDTO.getPhoneNumber().value(),
                userDTO.getType(), userDTO.getServices());
    }

    public UserDTO mapToDTO(User user){
        return new UserDTO(new SerialNumber(user.getSerialNumber()),
                user.getName(), new PhoneNumber(user.getPhoneNumber()), user.getType(), user.getServices());
    }
}
