package com.example.usm.repository;

import com.example.usm.entity.Service;
import com.example.usm.enums.ServiceStatus;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "service")
public interface ServiceRepository extends CouchbaseRepository<Service, Integer> {

     List<Service> findByVendor(String vendor);

     List<Service> findByStatus(ServiceStatus status);

     List<Service> findByVendorAndStatus(String vendor, ServiceStatus status);
}
