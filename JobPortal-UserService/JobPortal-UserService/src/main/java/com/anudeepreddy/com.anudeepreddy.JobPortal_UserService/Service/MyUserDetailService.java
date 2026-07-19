package com.anudeepreddy.JobPortal_UserService.Service;

import com.anudeepreddy.JobPortal_UserService.DTO.LoginDTO;
import com.anudeepreddy.JobPortal_UserService.Exception.UserNotFoundException;
import com.anudeepreddy.JobPortal_UserService.Model.UserPrincipal;
import com.anudeepreddy.JobPortal_UserService.Model.Users;
import com.anudeepreddy.JobPortal_UserService.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userMail) throws UsernameNotFoundException {
        Users user =userRepo.findByUserMail(userMail).orElseThrow(()->new UserNotFoundException("User Not Found!!!"));
        return new UserPrincipal(user);
    }
}
