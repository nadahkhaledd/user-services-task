package com.example.usm.service.services;

import com.example.usm.enums.ServiceStatus;
import com.example.usm.exception.service.ServiceNotFoundException;
import com.example.usm.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService implements IServicesService{

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServicesService(ServiceRepository serviceRepository){
        this.serviceRepository = serviceRepository;
    }

    @Override
    public com.example.usm.entity.Service add(com.example.usm.entity.Service service) {
        if(service == null)
            throw new NullPointerException();
        return serviceRepository.findById(service.getUid()).orElse(serviceRepository.save(service));
    }

    @Override
    public List<com.example.usm.entity.Service> getAll() {
        return (List<com.example.usm.entity.Service>) serviceRepository.findAll();
    }

    @Override
    public com.example.usm.entity.Service findByUID(int uid) {
        return serviceRepository.findById(uid).orElseThrow(ServiceNotFoundException::new);
    }

    @Override
    public List<com.example.usm.entity.Service> findByVendor(String vendor) {
        return serviceRepository.findByVendor(vendor);
    }

    @Override
    public List<com.example.usm.entity.Service> findByStatus(ServiceStatus status) {
        return serviceRepository.findByStatus(status);
    }

    @Override
    public List<com.example.usm.entity.Service> findByVendorAndStatus(String vendor, ServiceStatus status) {
        return serviceRepository.findByVendorAndStatus(vendor, status);
    }
}
