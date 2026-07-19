package com.anudeepreddy.JobPortal_UserService.Service;

import com.anudeepreddy.JobPortal_UserService.DTO.*;
import com.anudeepreddy.JobPortal_UserService.DTO.FeignUserDTO;
import com.anudeepreddy.JobPortal_UserService.Exception.*;
import com.anudeepreddy.JobPortal_UserService.Exception.UserNotFoundException;
import com.anudeepreddy.JobPortal_UserService.Model.Role;
import com.anudeepreddy.JobPortal_UserService.Model.Users;
import com.anudeepreddy.JobPortal_UserService.Repo.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OTPGenerateService otpGenerateService;


    public String register(Users user) {
        System.out.println("registering");
        if(userRepo.existsByUserMail(user.getUserMail()))
            throw new MailExistException("Mail is already in use");
        if(userRepo.existsByUserPhoneNum(user.getUserPhoneNum()))
            throw new PhoneNumExistException("Phone is already used");
        user.setUserId("USER"+ UUID.randomUUID().toString().substring(0,8));
        user.setUserCreatedAt(LocalDateTime.now());
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userRepo.save(user);
        return "User Added...";
    }

    public Page<GetUserDTO> getUsers(Pageable pageable) {

        return userRepo.findAll(pageable)
                .map(users -> new GetUserDTO(
                        users.getUserFullName(),
                        users.getUserMail(),
                        users.getUserPhoneNum(),
                        users.getUserLocation(),
                        users.getUserSkills(),
                        users.getRole(),
                        users.getUserExperience()
                ));
    }

    public String login(LoginDTO loginDTO) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserMail(),loginDTO.getUserPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(loginDTO.getUserMail());
        return "Invalid User!!!";
    }

    public GetUserDTO getUserByUsermail(String userMail) {
        Users users=userRepo.findByUserMail(userMail).orElseThrow(()->new UsernameNotFoundException("User Not found"));
        return new GetUserDTO(
                users.getUserFullName(),
                users.getUserMail(),
                users.getUserPhoneNum(),
                users.getUserLocation(),
                users.getUserSkills(),
                users.getRole(),
                users.getUserExperience()
        );
    }

    public GetUserDTO getUserProfileByUserMail(String userMail) {

        Users users=userRepo.findByUserMail(userMail).orElseThrow(()->new UserNotFoundException("User Not Found!!!"));

        return new GetUserDTO(
                users.getUserFullName(),
                users.getUserMail(),
                users.getUserPhoneNum(),
                users.getUserLocation(),
                users.getUserSkills(),
                users.getRole(),
                users.getUserExperience()
        );
    }

    public Users updateUserProfile(String userMail, UpdateProfileDTO updateProfileDTO) {

        Users users=userRepo.findByUserMail(userMail).orElseThrow(()->new UserNotFoundException("User not Found!!"));


        if(updateProfileDTO.getUserFullName()!=null) {
            if(updateProfileDTO.getUserFullName().isBlank())
                throw new CantBeBlankException("Full Name Can't be Blank!!!!");
            users.setUserFullName(updateProfileDTO.getUserFullName());
        }

        if(updateProfileDTO.getUserLocation()!=null) {
            if(updateProfileDTO.getUserLocation().isBlank())
                throw new CantBeBlankException("Location Can't be Blank!!!!");
            users.setUserLocation(updateProfileDTO.getUserLocation());
        }

        if(updateProfileDTO.getUserSkills()!=null) {
            if(updateProfileDTO.getUserSkills().isBlank())
                throw new CantBeBlankException("Skills Can't be Blank!!!!");
            users.setUserSkills(updateProfileDTO.getUserSkills());
        }

        if(updateProfileDTO.getUserMail()!=null ) {
            if(updateProfileDTO.getUserMail().isBlank())
                throw new CantBeBlankException("User Mail Can't be Blank!!!");
            if(!updateProfileDTO.getUserMail().equals(users.getUserMail())) {
                if (userRepo.existsByUserMail(updateProfileDTO.getUserMail()))
                    throw new MailExistException("Mail already exist!!!!");
                users.setUserMail(updateProfileDTO.getUserMail());
            }
        }

        if(updateProfileDTO.getUserPhoneNum()!=null ) {
            if(updateProfileDTO.getUserPhoneNum().isBlank())
                throw new CantBeBlankException("Phone Number Can't be Blank!!!");
            if (!updateProfileDTO.getUserPhoneNum().equals(users.getUserPhoneNum())) {
                if (userRepo.existsByUserPhoneNum(updateProfileDTO.getUserPhoneNum()))
                    throw new PhoneNumExistException("Phone already exist!!!!");
                users.setUserPhoneNum(updateProfileDTO.getUserPhoneNum());
            }
        }

        if(updateProfileDTO.getUserExperience()!=null) {
            if(updateProfileDTO.getUserExperience()<0)
                throw new InvalidException("UserExperience Can't be Negative!!!!");
            users.setUserExperience(updateProfileDTO.getUserExperience());
        }


        return userRepo.save(users);

    }


    public Users changeUserProfile(String userMail, UpdateProfileDTO updateProfileDTO) {

        Users users=userRepo.findByUserMail(userMail).orElseThrow(()-> new UserNotFoundException("User Not found!!!"));

        if (updateProfileDTO.getUserFullName() == null || updateProfileDTO.getUserFullName().isBlank())
            throw new CantBeBlankException("Full Name can't be blank");

        if (updateProfileDTO.getUserMail() == null || updateProfileDTO.getUserMail().isBlank())
            throw new CantBeBlankException("Mail can't be blank");

        if (updateProfileDTO.getUserPhoneNum() == null || updateProfileDTO.getUserPhoneNum().isBlank())
            throw new CantBeBlankException("Phone Number can't be blank");

        if (updateProfileDTO.getUserLocation() == null || updateProfileDTO.getUserLocation().isBlank())
            throw new CantBeBlankException("Location can't be blank");

        if (updateProfileDTO.getUserSkills() == null || updateProfileDTO.getUserSkills().isBlank())
            throw new CantBeBlankException("Skills can't be blank");

        if (updateProfileDTO.getUserExperience() == null || updateProfileDTO.getUserExperience() < 0)
            throw new InvalidException("Experience can't be negative");

        if (!updateProfileDTO.getUserMail().equals(users.getUserMail())
                && userRepo.existsByUserMail(updateProfileDTO.getUserMail()))
            throw new MailExistException("Mail already exists");

        if (!updateProfileDTO.getUserPhoneNum().equals(users.getUserPhoneNum())
                && userRepo.existsByUserPhoneNum(updateProfileDTO.getUserPhoneNum()))
            throw new PhoneNumExistException("Phone already exists");

        users.setUserFullName(updateProfileDTO.getUserFullName());
        users.setUserMail(updateProfileDTO.getUserMail());
        users.setUserPhoneNum(updateProfileDTO.getUserPhoneNum());
        users.setUserLocation(updateProfileDTO.getUserLocation());
        users.setUserSkills(updateProfileDTO.getUserSkills());
        users.setUserExperience(updateProfileDTO.getUserExperience());

        return userRepo.save(users);
    }

    public Users changePassword(String userMail, ChangePasswordDTO changePasswordDTO) {

        Users users=userRepo.findByUserMail(userMail).orElseThrow(()->new UserNotFoundException("Invalid User Name and Password!!!"));

        if(changePasswordDTO.getOldPassword().isBlank())
            throw new CantBeBlankException("Old Password Place can't be blank!!");
        if(changePasswordDTO.getNewPassoword().isBlank())
            throw new CantBeBlankException("New Password Place can't be blank!!!");
        if(!passwordEncoder.matches(changePasswordDTO.getOldPassword(),users.getUserPassword()))
            throw new PasswordDoesntMatchException("Old password doesn't matched enter crt one!!!!");
        if(passwordEncoder.matches(changePasswordDTO.getNewPassoword(),users.getUserPassword()))
            throw new PasswordDoesntMatchException("New shouldn't as Old password!!!");
        users.setUserPassword(passwordEncoder.encode(changePasswordDTO.getNewPassoword()));
        return userRepo.save(users);

    }


    public Users generateOTP(OTPDTO forgotPasswordDTO) {
        Users users=userRepo.findByUserMail(forgotPasswordDTO.getUserMail()).orElseThrow(()->new UserNotFoundException("Unable to fetch user!!!"));
        String generatedOTP=otpGenerateService.generateOTP();
        users.setOtp(generatedOTP);
        users.setOtpExp(LocalDateTime.now().plusMinutes(5));
        return userRepo.save(users);
    }


    public Users resetPassword(@Valid ResetPasswordDTO resetPasswordDTO) {
        Users users=userRepo.findByUserMail(resetPasswordDTO.getUserMail()).orElseThrow(()->new OTPDoesntMatchException("Invalid Usermail"));
        System.out.println(users.getOtp()+"   "+users.getOtpExp());
        if(users.getOtpExp().isBefore(LocalDateTime.now()))
            throw new OTPExpirationException("OTP has Expired");
        if(!resetPasswordDTO.getOtp().equals(users.getOtp()))
            throw new OTPDoesntMatchException("Invalid otp");
        if(!resetPasswordDTO.getResetPassword().equals(resetPasswordDTO.getConfirmPassword()))
            throw new PasswordDoesntMatchException("Reset Password and Confirm Password doesn't match");
        users.setUserPassword(passwordEncoder.encode(resetPasswordDTO.getConfirmPassword()));
        users.setOtp(null);
        users.setOtpExp(null);
        return userRepo.save(users);
    }




    public Users verifyEmail(String userMail,@Valid VerifyEmailDTO verifyEmailDTO) {
        Users users=userRepo.findByUserMail(userMail).orElseThrow(()-> new UserNotFoundException("Invalid User Details"));
        if(users.getOtpExp().isBefore(LocalDateTime.now()))
            throw new OTPExpirationException("OTP Expired!!!!");
        if(!verifyEmailDTO.getOtp().equals(users.getOtp()))
            throw new OTPDoesntMatchException("OTP Doesn't Match");
        users.setVerified(true);
        return userRepo.save(users);
    }

    public void deleteUserByAdmin(String userMail,DeleteMailDTO deleteMailDTO) {
        Users user=userRepo.findByUserMail(userMail).orElseThrow(()->new UserNotFoundException("User Not Found"));
        Users deleteUser=userRepo.findByUserMail(deleteMailDTO.getUserMail()).orElseThrow(()->new UserNotFoundException("User Not found"));
        if(user.getRole()!= Role.ADMIN)
            throw new NotAdminException("Only ADMIN can do it!!!!");
        userRepo.delete(deleteUser);
    }

    public void deleteUser(String userMail) {
        Users user=userRepo.findByUserMail(userMail).orElseThrow(()->new UserNotFoundException("User Not Found!!!"));
        userRepo.delete(user);
    }


    public Page<GetUserDTO> getUserBySkill(String userMail,String userSkill, Pageable pageable) {
        Users users=userRepo.findByUserMail(userMail).orElseThrow(()-> new UserNotFoundException("User not found"));
        if(users.getRole()!=Role.ADMIN)
            throw new NotAdminException("Only ADMIN Can do it");
        return userRepo.findByUserSkillsContainingIgnoreCase(userSkill,pageable)
                .map(user -> new GetUserDTO(
                        user.getUserFullName(),
                        user.getUserMail(),
                        user.getUserPhoneNum(),
                        user.getUserLocation(),
                        user.getUserSkills(),
                        user.getRole(),
                        user.getUserExperience()

                ));
    }


    public FeignUserDTO getInternalUserRole(String userId) {
        Users user=userRepo.findByUserId(userId).orElseThrow(()->new UserNotFoundException("User Not Found!!!!"));
        return new FeignUserDTO(
                user.getUserId(),
                user.getRole()
        );
    }
}
