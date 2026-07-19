package com.anudeepreddy.JobPortal_UserService.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {

    @Size(min=4,message = "Old should be greater than 4 chars")
    @NotBlank(message = "Old password shouldn't be blank")
    private String oldPassword;
    @Size(min=4,message = "New should be greater than 4 chars")
    @NotBlank(message = "New password shouldn't be blank")
    private String newPassoword;
}
