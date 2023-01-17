package com.example.usm.entity;

import com.example.usm.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
@Table(name = "Users")
public class User {

    @Id
    @Column(length = 19, unique = true)
    private String serialNumber;

    @Column(length = 50)
    @NotNull @NotBlank
    private String name;

    @Column(length = 14, unique = true)
    @NotNull @NotBlank
    private String phoneNumber;

    @Column
    @Enumerated
    @NotNull
    private UserType type;

}
