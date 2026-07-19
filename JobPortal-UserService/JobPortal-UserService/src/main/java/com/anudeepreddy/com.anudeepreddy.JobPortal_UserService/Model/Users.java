package com.anudeepreddy.JobPortal_UserService.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userId;
    @NotBlank(message = "Name shouldn't be blank")
    private String userFullName;
    @NotBlank(message = "Mail shouldn't be blank")
    @Column(unique = true)
    @Email(message = "Enter a valid mail id")
    private String userMail;
    @NotBlank(message = "Password shouldn't be blank")
    @Size(min = 4,message = "Password should be greater than 4 chars")
    private String userPassword;
    @NotBlank(message = "Phn shouldn't be blank")
    private String userPhoneNum;
    @NotBlank(message = "Location shouldn't be blank")
    private String userLocation;
    @NotBlank(message = "Skills shouldn't be blank")
    private String userSkills;
    @NotNull(message = "Role shouldn't be blank")
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime userCreatedAt;
    @NotNull(message = "Experience shouldn't be blank")
    @PositiveOrZero(message = "Experience shouldn't be negative")
    private Integer userExperience;
    private String otp;
    private LocalDateTime otpExp;
    private boolean isVerified;

}
