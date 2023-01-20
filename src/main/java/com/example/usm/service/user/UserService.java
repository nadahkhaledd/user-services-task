package com.example.usm.service.user;

import com.example.usm.entity.User;
import com.example.usm.enums.UserType;
import com.example.usm.enums.UserFieldName;
import com.example.usm.exception.DuplicateEntryException;
import com.example.usm.exception.service.MaximumNumberOfServicesReachedException;
import com.example.usm.exception.user.UserNotFoundException;
import com.example.usm.repository.ServiceRepository;
import com.example.usm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;

    private final ServiceRepository serviceRepository;

    @Autowired
    public UserService(UserRepository userRepository, ServiceRepository serviceRepository){
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
    }


    @Override
    public User add(User user) {
        if(user==null)
            throw new NullPointerException();

        if(userRepository.existsById(user.getSerialNumber()))
            throw new DuplicateEntryException();
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
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

    @Override
    public void addUserService(com.example.usm.entity.Service service, String serialNumber) {
        if(service == null)
            throw new NullPointerException();

        int numberOfCurrentServices = userRepository.findNumberOfUserServices(serialNumber);
        if(numberOfCurrentServices< 10){
            if(serviceRepository.existsById(service.getUid()))
                throw new DuplicateEntryException();

            userRepository.addService(service, serialNumber);
            serviceRepository.save(service);
        }
        else{
            throw new MaximumNumberOfServicesReachedException();
        }
    }

}
