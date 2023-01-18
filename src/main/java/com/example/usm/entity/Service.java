package com.example.usm.entity;

import com.example.usm.enums.ServiceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(length = 70, nullable = false)
    @NotNull @NotBlank
    private String vendor;

    @Column(nullable = false)
    @NotNull
    private Date dateCreated;

    @Column
    @Enumerated
    @NotNull
    private ServiceStatus status;
}
