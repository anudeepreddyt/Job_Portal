package com.anudeepreddy.JobPortal_ApplicationService.Service;

import com.anudeepreddy.JobPortal_ApplicationService.Client.JobClient;
import com.anudeepreddy.JobPortal_ApplicationService.Client.UserClient;
import com.anudeepreddy.JobPortal_ApplicationService.DTO.*;
import com.anudeepreddy.JobPortal_ApplicationService.Exception.ApplicationNotFoundException;
import com.anudeepreddy.JobPortal_ApplicationService.Exception.OnlyAdminException;
import com.anudeepreddy.JobPortal_ApplicationService.Exception.UserAppliedForJobAlreadyException;
import com.anudeepreddy.JobPortal_ApplicationService.Exception.WrongUserException;
import com.anudeepreddy.JobPortal_ApplicationService.Model.Application;
import com.anudeepreddy.JobPortal_ApplicationService.Model.ApplicationStatus;
import com.anudeepreddy.JobPortal_ApplicationService.Model.Role;
import com.anudeepreddy.JobPortal_ApplicationService.Repo.ApplicationRepo;
import com.sun.tools.attach.AttachOperationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.AccessibleObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ApplicationService {



    @Autowired
    private ApplicationRepo applicationRepo;
    @Autowired
    private UserClient userClient;
    @Autowired
    private JobClient jobClient;


    public void applyJob(ApplicationDTO applicationDTO) {


        FeignUserDTO user=userClient.getInternalUserRole(applicationDTO.getUserId());
        System.out.println(user.getUserId());
        System.out.println(applicationDTO.getUserId());
        FeignJobDTO job=jobClient.getJobStatusInternally(applicationDTO.getJobId());
        System.out.println(job);

        if(user.getRole()!= Role.JOB_SEEKER)
            throw new WrongUserException("Only Job seeker can apply for job!!!");
        if(!job.getActive())
            throw new WrongUserException("This job is not active you can't apply");
    Application application=new Application();

    if(applicationRepo.existsByUserIdAndJobId(applicationDTO.getUserId(),applicationDTO.getJobId())){
        throw new UserAppliedForJobAlreadyException("UserId already registered for this job!!!");
    }

    application.setApplicationId("APP"+ UUID.randomUUID().toString().substring(0,8));
    application.setUserId(applicationDTO.getUserId());
    application.setJobId(applicationDTO.getJobId());
    application.setCoverLetter(applicationDTO.getCoverLetter());
    application.setResumeUrl(applicationDTO.getResumeUrl());
    application.setStatus(ApplicationStatus.APPLIED);
    application.setAppliedOn(LocalDateTime.now());
    application.setUpdatedOn(null);

    applicationRepo.save(application);

    }

    public List<GetApplicationForUserDTO> getAppliedJobsByUserId(String userId) {

        List<Application> applications=applicationRepo.findByUserId(userId);
        if(applications.isEmpty())
            throw new ApplicationNotFoundException("No found by this userId");
        List<GetApplicationForUserDTO> response=new ArrayList<>();
        for(Application application:applications){
            GetApplicationForUserDTO dto=new GetApplicationForUserDTO();
            dto.setApplicationId(application.getApplicationId());
            dto.setJobId(application.getJobId());
            dto.setResumeUrl(application.getResumeUrl());
            dto.setAppliedOn(application.getAppliedOn());
            dto.setStatus(application.getStatus());
            dto.setCoverLetter(application.getCoverLetter());

            response.add(dto);

        }
        return response;
    }

    public List<GetApplicationByJobIdDTO> getApplicantByJobId(String jobId) {

        List<Application> applications=applicationRepo.findByJobId(jobId);
        if (applications.isEmpty()){
            throw new ApplicationNotFoundException("No application found");
        }
        List<GetApplicationByJobIdDTO> respones=new ArrayList<>();
        for(Application application:applications){
            GetApplicationByJobIdDTO dto=new GetApplicationByJobIdDTO();
            dto.setApplicationId(application.getApplicationId());
            dto.setUserId(application.getUserId());
            dto.setUpdatedOn(application.getUpdatedOn());
            dto.setResumeUrl(application.getResumeUrl());
            dto.setStatus(application.getStatus());
            dto.setAppliedOn(application.getAppliedOn());
            dto.setCoverLetter(application.getCoverLetter());
            respones.add(dto);
        }
        return respones;
    }

    public String updateApplicationStatus(String applicationId,ApplicationStatus newStatus,ApplicationStatusDTO applicationStatusDTO) {
        FeignUserDTO user=userClient.getInternalUserRole(applicationStatusDTO.getUserId());
        if(user.getRole()!=Role.ADMIN)
            throw new OnlyAdminException("Only admin can change status");
        Application application=applicationRepo.findByApplicationId(applicationId).orElseThrow(()->new ApplicationNotFoundException("Application Not Found!!!!"));
        application.setStatus(newStatus);
        applicationRepo.save(application);
        return "Status Changed Successfully...";
    }

    public GetApplicationByApplicationId getApplicationByApplicationId(String applicationId) {
        Application application=applicationRepo.findByApplicationId(applicationId).orElseThrow(()->new ApplicationNotFoundException("No application found on this application id"));
        GetApplicationByApplicationId dto=new GetApplicationByApplicationId();
        dto.setUserId(application.getUserId());
        dto.setJobId(application.getJobId());
        dto.setResumeUrl(application.getResumeUrl());
        dto.setAppliedOn(application.getAppliedOn());
        dto.setStatus(application.getStatus());
        dto.setCoverLetter(application.getCoverLetter());
        return dto;
    }

    public String deleteApplicationByApplicationId(String applicationId) {
        Application application=applicationRepo.findByApplicationId(applicationId).orElseThrow(()-> new ApplicationNotFoundException("Application Not Found!!!"));
        applicationRepo.delete(application);
        return "Application Deleted Successfully...";
    }
}
