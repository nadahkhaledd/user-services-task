package com.example.usm.service.services;

import com.example.usm.entity.Service;
import com.example.usm.enums.ServiceStatus;

import java.util.List;


public interface IServicesService {

    Service add(Service service);

    List<Service> getAll();

    Service findByUID(long uid);

    List<Service> findByVendor(String vendor);

    List<Service> findByStatus(ServiceStatus status);

    List<Service> findByVendorAndStatus(String vendor, ServiceStatus status);

}
