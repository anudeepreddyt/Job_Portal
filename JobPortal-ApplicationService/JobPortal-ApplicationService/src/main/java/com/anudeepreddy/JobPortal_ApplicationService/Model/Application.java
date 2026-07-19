package com.anudeepreddy.JobPortal_ApplicationService.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Application {

    @Id
    private String applicationId;
    private String jobId;
    private String userId;
    private String coverLetter;
    private String resumeUrl;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private LocalDateTime appliedOn;
    private LocalDateTime updatedOn;

}
