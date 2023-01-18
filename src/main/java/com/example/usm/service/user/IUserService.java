package com.example.usm.service.user;

import com.example.usm.entity.User;
import com.example.usm.enums.UserType;

import java.util.List;

public interface IUserService {

     User add(User user);

     List<User> getAll();

     User findBySN(String serialNumber);

     User findByPhone(String phoneNumber);

     List<User> findByType(UserType type);

}
