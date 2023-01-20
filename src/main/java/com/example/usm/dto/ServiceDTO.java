package com.example.usm.dto;

import com.example.usm.enums.ServiceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ServiceDTO {
    private int uid;
    @NotNull
    private String vendor;
    private Date dateCreated = new java.sql.Date(System.currentTimeMillis());
    @NotNull
    private ServiceStatus status;

}
