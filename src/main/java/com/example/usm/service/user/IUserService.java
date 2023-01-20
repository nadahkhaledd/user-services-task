package com.example.usm.service.user;

import com.example.usm.entity.Service;
import com.example.usm.entity.User;
import com.example.usm.enums.UserType;

import java.util.List;
import java.util.Set;

public interface IUserService {

     User add(User user);

     List<User> getAll();

     User findBySN(String serialNumber);

     User findByPhone(String phoneNumber);

     List<User> findByType(UserType type);

     //List<Service> findUserServicesBySN(String serialNumber);

     int getNumberOfUserServicesBySN(String serialNumber);

     void addUserService(Service service, String serialNumber);

}
