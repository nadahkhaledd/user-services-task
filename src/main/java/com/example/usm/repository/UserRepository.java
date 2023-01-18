package com.example.usm.repository;

import com.example.usm.entity.Service;
import com.example.usm.entity.User;
import com.example.usm.enums.UserType;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@N1qlPrimaryIndexed
public interface UserRepository extends CouchbaseRepository<User, String> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    List<User> findByType(UserType type);
}
