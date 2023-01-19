package com.example.usm.entity;

import com.example.usm.enums.UserType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
public class User {

    @Id
    private String serialNumber;

    @Field
    private String name;

    @Field
    private String phoneNumber;

    @Field
    private UserType type;

    @ToString.Exclude
    @Field
    private List<Service> services = new ArrayList<>();

}
