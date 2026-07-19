package com.anudeepreddy.JobPortal_ApplicationService.DTO;


import com.anudeepreddy.JobPortal_ApplicationService.Model.Application;
import com.anudeepreddy.JobPortal_ApplicationService.Model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetApplicationForUserDTO {

    private String applicationId;
    private String jobId;
    private String resumeUrl;
    private LocalDateTime appliedOn;
    private ApplicationStatus status;
    private String coverLetter;

}
