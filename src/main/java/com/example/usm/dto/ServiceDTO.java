package com.example.usm.dto;

import com.example.usm.entity.User;
import com.example.usm.enums.ServiceStatus;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ServiceDTO {
    private long uid;
    private String vendor;
    private Date dateCreated;
    private ServiceStatus status;
}
