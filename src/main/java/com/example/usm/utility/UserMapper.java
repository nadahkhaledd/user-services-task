package com.example.usm.utility;

import com.example.usm.domain_primitive.PhoneNumber;
import com.example.usm.domain_primitive.SerialNumber;
import com.example.usm.dto.UserDTO;
import com.example.usm.dto.UserResponseDTO;
import com.example.usm.entity.User;

public class UserMapper {

    public User mapToUser(UserDTO userDTO){

        return new User(userDTO.getSerialNumber().value(),
                userDTO.getName(), userDTO.getPhoneNumber().value(),
                UserUtils.getUserType(userDTO.getSerialNumber().value()), userDTO.getServices());
    }

    public UserResponseDTO mapToResponseDTO(User user){
        return new UserResponseDTO(new SerialNumber(user.getSerialNumber()),
                user.getName(), new PhoneNumber(user.getPhoneNumber()), user.getType(), user.getServices());
    }

}
