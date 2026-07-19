package com.anudeepreddy.JobPortal_UserService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.anudeepreddy.JobPortal_UserService.Model.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeignUserDTO {

    private String userId;
    private Role role;

}
