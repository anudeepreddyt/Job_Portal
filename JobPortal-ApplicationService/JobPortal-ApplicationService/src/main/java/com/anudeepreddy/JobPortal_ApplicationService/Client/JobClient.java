package com.anudeepreddy.JobPortal_ApplicationService.Client;

import com.anudeepreddy.JobPortal_ApplicationService.DTO.FeignJobDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="JobPortal-Job-Service")
public interface JobClient {

    @GetMapping("/job/internally/{jobId}")
    FeignJobDTO getJobStatusInternally(@PathVariable String jobId);
}
