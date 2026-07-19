package com.anudeepreddy.JobPortal_ApplicationService.Client;

import com.anudeepreddy.JobPortal_ApplicationService.DTO.FeignUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="JobPortal-UserService")
public interface UserClient {

    @GetMapping("/users/internal/{userId}")
    FeignUserDTO getInternalUserRole(@PathVariable String userId);

}
