package com.anudeepreddy.JobPortal_ApplicationService.DTO;

import com.anudeepreddy.JobPortal_ApplicationService.Model.ApplicationStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetApplicationByJobIdDTO {

    private String applicationId;
    private String userId;
    private String coverLetter;
    private String resumeUrl;
    private ApplicationStatus status;
    private LocalDateTime appliedOn;
    private LocalDateTime updatedOn;
}
