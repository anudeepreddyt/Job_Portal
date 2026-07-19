package com.anudeepreddy.JobPortal_UserService.DTO;

import com.anudeepreddy.JobPortal_UserService.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileDTO {  private String userFullName;
    private String userMail;
    private String userPhoneNum;
    private String userLocation;
    private String userSkills;
    private Integer userExperience;
}
