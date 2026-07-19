package com.anudeepreddy.JobPortal_UserService.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordDTO {

    @Email(message = "Invalid email enter crt one")
    private String userMail;
    @Size(min=4,message = "It should be min 4 chars")
    private String resetPassword;
    @Size(min=4,message = "It should be min 4 chars")
    private String confirmPassword;
    @Size(min=6,max=6)
    private String otp;
}
