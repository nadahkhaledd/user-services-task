package com.example.usm.repository;

import com.example.usm.entity.Service;
import com.example.usm.entity.User;
import com.example.usm.enums.UserType;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "user")
public interface UserRepository extends CouchbaseRepository<User, String> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    List<User> findByType(UserType type);

    @Query("""
            SELECT ARRAY_COUNT(services) AS total_services
            FROM #{#n1ql.bucket}
            WHERE meta().id = $1
            """)
    int findNumberOfUserServices(String serialNumber);

    @Transactional
    @Query("""
            UPDATE #{#n1ql.bucket}
            SET services = ARRAY_APPEND(services, $1)
            WHERE meta().id= $2""")
    void addService(Service service, String serialNumber);


}
