package com.anudeepreddy.JobPortal_ApplicationService.DTO;

import com.anudeepreddy.JobPortal_ApplicationService.Model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {

    private String userId;
    private String jobId;
    private String coverLetter;
    private String resumeUrl;
}