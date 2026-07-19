package com.anudeepreddy.JobPortal_UserService.Repo;

import com.anudeepreddy.JobPortal_UserService.DTO.GetUserDTO;
import com.anudeepreddy.JobPortal_UserService.Model.Users;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users,Integer> {
    boolean existsByUserMail(String userMail);
    boolean existsByUserPhoneNum(String userPhoneNum);

    Optional<Users> findByUserMail(String userMail);


    Page<Users> findByUserSkillsContainingIgnoreCase(String userSkill, Pageable pageable);


    Optional<Users> findByUserId(String userId);
}
