package com.anudeepreddy.JobPortal_ApplicationService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeignJobDTO {

    private String jobId;
    private Boolean active;
}
