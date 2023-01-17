package com.example.usm.dto;

import com.example.usm.entity.User;
import com.example.usm.enums.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ServiceDTO {
    private long uid;
    private String vendor;
    private Date dateCreated;
    private ServiceStatus status;
}
