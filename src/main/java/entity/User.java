package entity;

import enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String serialNumber;

    @Column(length = 50)
    @NotNull @NotBlank
    private String name;

    @Column(length = 14)
    @Min(8) @Max(14)
    @NotNull @NotBlank
    private String phoneNumber;

    @Column
    @Enumerated
    @NotNull
    private UserType type;




}
