package com.example.usm.repository;

import com.example.usm.entity.Service;
import com.example.usm.entity.User;
import com.example.usm.enums.UserType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    List<User> findByType(UserType type);

}
