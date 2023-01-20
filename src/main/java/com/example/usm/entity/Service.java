package com.example.usm.entity;

import com.example.usm.enums.ServiceStatus;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import java.util.Date;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Service {

    @Id
    @GeneratedValue(strategy = UNIQUE)
    private int uid;

    @Field
    @NotNull @NotBlank
    private String vendor;

    @Field
    @NotNull
    private Date dateCreated;

    @Field
    @NotNull
    private ServiceStatus status;
}
