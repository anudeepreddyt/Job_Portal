package com.anudeepreddy.JobPortal_ApplicationService.Controllers;

import com.anudeepreddy.JobPortal_ApplicationService.DTO.*;
import com.anudeepreddy.JobPortal_ApplicationService.Model.ApplicationStatus;
import com.anudeepreddy.JobPortal_ApplicationService.Service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
@Tag(name = "Application Management API'S")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Operation(summary = "For applying Job")
    @PostMapping("/applyjob")
    public ResponseEntity<String> applyJob(@Valid @RequestBody ApplicationDTO applicationDTO){
        applicationService.applyJob(applicationDTO);
        return ResponseEntity.ok("Applied for this job successfully....");
    }

    @Operation(summary = "For Getting Applied jobs by userId")
    @GetMapping("/getappliedjobsbyuserid/{userId}")
    public ResponseEntity<List<GetApplicationForUserDTO>> getAppliedJobsByUserId(@PathVariable String userId){

        return new ResponseEntity<>(applicationService.getAppliedJobsByUserId(userId), HttpStatus.OK);
    }

    @Operation(summary = "Get application details by jobid(recruters)")
    @GetMapping("/getapplicantsbyjodid/{jobId}")
    public ResponseEntity<List<GetApplicationByJobIdDTO>> getApplicantsByJobId(@PathVariable String jobId){
        return new ResponseEntity<>(applicationService.getApplicantByJobId(jobId),HttpStatus.OK);
    }

    @Operation(summary = "Updation Application Status")
    @PostMapping("/updateapplicationstatus/{applicationId}")
    public ResponseEntity<String> updateApplicationStatus(@PathVariable String applicationId, @RequestParam ApplicationStatus newStatus, @RequestBody ApplicationStatusDTO applicationStatusDTO){
        return new ResponseEntity<>(applicationService.updateApplicationStatus(applicationId,newStatus,applicationStatusDTO),HttpStatus.OK);
    }

    @Operation(summary = "Get Application by applicationId")
    @GetMapping("/getapplicationbyapplicationid/{applicationId}")
    public ResponseEntity<GetApplicationByApplicationId> getApplicationByApplicationId(@PathVariable String applicationId){
        return new ResponseEntity<>(applicationService.getApplicationByApplicationId(applicationId),HttpStatus.OK);
    }

    @Operation(summary = "Get Application by applicationId")
    @DeleteMapping("/deleteapplicationbyapplicationid/{applicationId}")
    public ResponseEntity<String> deleteApplicationByApplicationId(@PathVariable String applicationId){
        return new ResponseEntity<>(applicationService.deleteApplicationByApplicationId(applicationId),HttpStatus.OK);
    }
}
