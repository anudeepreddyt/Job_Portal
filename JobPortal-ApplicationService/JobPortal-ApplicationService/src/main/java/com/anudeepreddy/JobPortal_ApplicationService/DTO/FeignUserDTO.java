package com.anudeepreddy.JobPortal_ApplicationService.DTO;

import com.anudeepreddy.JobPortal_ApplicationService.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeignUserDTO {

    private String userId;
    private Role role;
}
