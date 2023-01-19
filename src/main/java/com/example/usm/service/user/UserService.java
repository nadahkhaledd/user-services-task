package com.example.usm.service.user;

import com.example.usm.entity.User;
import com.example.usm.enums.UserType;
import com.example.usm.enums.UserFieldName;
import com.example.usm.exception.user.UserNotFoundException;
import com.example.usm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    private  UserRepository userRepository;



    @Override
    public User add(User user) {
        return userRepository.findById(user.getSerialNumber()).orElse(userRepository.save(user));
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findBySN(String serialNumber) {
        return userRepository.findById(serialNumber).orElseThrow(() -> new UserNotFoundException(UserFieldName.SerialNumber));
    }

    @Override
    public User findByPhone(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new UserNotFoundException(UserFieldName.PhoneNumber));
    }

    @Override
    public List<User> findByType(UserType type) {
        return userRepository.findByType(type);
    }

//    @Override
//    public void addUserService(com.example.usm.entity.Service service, String serialNumber) {
////        User user = findBySN(serialNumber);
////        Set<com.example.usm.entity.Service> services = user.getServices();
////        services.add(service);
//        userRepository.updateServicesBySerialNumber(service, serialNumber);
//    }
}
