package com.example.usm.repository;

import com.example.usm.entity.Service;
import com.example.usm.entity.User;
import com.example.usm.enums.UserType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Transactional
    @Modifying
    @Query("update User u set u.services = ?1 where u.serialNumber = ?2")
    void updateServicesBySerialNumber(Set<Service> services, String serialNumber);

    Optional<User> findByPhoneNumber(String phoneNumber);

    List<User> findByType(UserType type);

}
