package com.anudeepreddy.JobPortal_ApplicationService.Repo;

import com.anudeepreddy.JobPortal_ApplicationService.Model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepo extends JpaRepository<Application,String> {

    List<Application> findByUserId(String userId);

    List<Application> findByJobId(String jobId);

    Optional<Application> findByApplicationId(String applicationId);

    boolean existsByUserIdAndJobId(String userId, String jobId);
}
