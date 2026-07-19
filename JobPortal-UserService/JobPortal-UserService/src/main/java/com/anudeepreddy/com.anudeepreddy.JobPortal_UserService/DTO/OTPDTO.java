package com.anudeepreddy.JobPortal_UserService.DTO;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OTPDTO {

    @Email(message = "Enter email crtly!!!!")
    private String userMail;
}
