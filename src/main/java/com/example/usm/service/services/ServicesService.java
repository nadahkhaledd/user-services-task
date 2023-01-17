package com.example.usm.service.services;

import com.example.usm.enums.ServiceStatus;
import com.example.usm.exception.service.ServiceNotFoundException;
import com.example.usm.exception.user.UserNotFoundException;
import com.example.usm.repository.ServiceRepository;
import com.example.usm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService implements IServicesService{

    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    @Autowired
    public ServicesService(ServiceRepository serviceRepository, UserRepository userRepository){
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public com.example.usm.entity.Service add(com.example.usm.entity.Service service) {
        userRepository.findById(service.getUser().getSerialNumber()).orElseThrow(UserNotFoundException::new);

        return serviceRepository.findById(service.getUid()).orElse(serviceRepository.save(service));
    }

    @Override
    public List<com.example.usm.entity.Service> getAll() {
        return (List<com.example.usm.entity.Service>) serviceRepository.findAll();
    }

    @Override
    public com.example.usm.entity.Service findByUID(long uid) {
        return serviceRepository.findById(uid).orElseThrow(ServiceNotFoundException::new);
    }

    @Override
    public List<com.example.usm.entity.Service> findByVendor(String vendor) {
        return serviceRepository.findByVendor(vendor);
    }

    @Override
    public List<com.example.usm.entity.Service> findByUser(String serialNumber) {
        return serviceRepository.findByUserSerialNumber(serialNumber);
    }

    @Override
    public List<com.example.usm.entity.Service> findByStatus(ServiceStatus status) {
        return serviceRepository.findByStatus(status);
    }
}
