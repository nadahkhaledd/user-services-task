package com.example.usm.repository;

import com.example.usm.entity.Service;
import com.example.usm.enums.ServiceStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {

    public List<Service> findByVendor(String vendor);

    public List<Service> findByStatus(ServiceStatus status);


}
