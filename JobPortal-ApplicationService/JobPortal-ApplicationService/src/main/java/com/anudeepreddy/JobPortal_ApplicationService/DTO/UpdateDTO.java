package com.anudeepreddy.JobPortal_ApplicationService.DTO;

import com.anudeepreddy.JobPortal_ApplicationService.Model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDTO {

    private ApplicationStatus status;
    private LocalDateTime updatedOn;
}
