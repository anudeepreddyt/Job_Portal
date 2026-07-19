package com.anudeepreddy.JobPortal_UserService.Controller;

import com.anudeepreddy.JobPortal_UserService.DTO.*;
import com.anudeepreddy.JobPortal_UserService.Model.Users;
import com.anudeepreddy.JobPortal_UserService.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.anudeepreddy.JobPortal_UserService.DTO.FeignUserDTO;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management API's")
public class UserController {

    @Autowired
    private UserService userService;


    @Operation(summary = "Register new User")
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody Users user)
    {
        String msg=userService.register(user);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @Operation(summary = "Get all users(Only ADMIN)")
    @GetMapping("/getusers")
    public ResponseEntity<Page<GetUserDTO>> getUsers(@RequestParam (defaultValue = "0")int page , @RequestParam (defaultValue = "3") int size){
        Pageable pageable=PageRequest.of(page,size);
        return  ResponseEntity.ok(userService.getUsers(pageable));
    }

    @Operation(summary = "Login User")
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }

    @Operation(summary = "Get User by Usermail (Only ADMIN)")
    @GetMapping("/{userMail}")
    public ResponseEntity<GetUserDTO> getUserByUsermail(@PathVariable String userMail)
    {
        return  ResponseEntity.ok(userService.getUserByUsermail(userMail));
    }

    @Operation(summary = "Get User Profile by Usermail (Logined User Data)")
    @GetMapping("/profile")
    public ResponseEntity<GetUserDTO> getUserProfileByUserMail(Authentication authentication)
    {
        String userMail=authentication.getName();
        return ResponseEntity.ok(userService.getUserProfileByUserMail(userMail));
    }

    @Operation(summary = "Update User Profile (Logged in User Data only)")
    @PatchMapping("/PatchUpdateProfile")
    public ResponseEntity<String> updateUserProfile(Authentication authentication,@RequestBody UpdateProfileDTO updateProfileDTO){
        String userMail=authentication.getName();
        userService.updateUserProfile(userMail,updateProfileDTO);

        return ResponseEntity.ok("Profile Updated Successfully....");
    }

    @Operation(summary = "Change User profile (Logged in User Data only)")
    @PutMapping("/putChangeProfile")
    public ResponseEntity<String> changeUserProfile(Authentication authentication,@Valid @RequestBody UpdateProfileDTO updateProfileDTO){
        String userMail=authentication.getName();
        userService.changeUserProfile(userMail,updateProfileDTO);
        return ResponseEntity.ok("Profile Changed Successfully...");
    }

    @Operation(summary = "Change Password ")
    @PatchMapping("/changePassword")
    public ResponseEntity<String> changePassword(Authentication authentication,@Valid @RequestBody ChangePasswordDTO changePasswordDTO ){
        String userMail=authentication.getName();
        userService.changePassword(userMail,changePasswordDTO);
        return  ResponseEntity.ok("Password Change Successfully...");
    }

    @Operation(summary = "Generate OTP")
    @PostMapping("/generateOTP")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody OTPDTO OTPDTO)
    {
        userService.generateOTP(OTPDTO);
        return ResponseEntity.ok("OTP generated Successufully...");
    }

    @Operation(summary = "Reset Password")
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO)
    {
        userService.resetPassword(resetPasswordDTO);
        return ResponseEntity.ok("Password Changed Successfully....");
    }

    @Operation(summary = "Verify Email")
    @PostMapping("/verifyEmail")
    public ResponseEntity<String> verifyEmail(Authentication authentication,@Valid @RequestBody VerifyEmailDTO verifyEmailDTO)
    {
        String userMail=authentication.getName();
        userService.verifyEmail(userMail,verifyEmailDTO);
        return ResponseEntity.ok("Email Verified Successfully...");
    }

    @Operation(summary = "Delete Users (ADMIN)")
    @DeleteMapping("/deleteUsers")
    public ResponseEntity<String> deleteUserByAdmin(Authentication authentication,@RequestBody DeleteMailDTO deleteMailDTO){
        String userMail=authentication.getName();
        userService.deleteUserByAdmin(userMail,deleteMailDTO);
        return ResponseEntity.ok("User Delete Successfully");
    }

    @Operation(summary = "Delete Users Logged in user")
    @DeleteMapping("/{userMail}")
    public ResponseEntity<String> deleteUser(@PathVariable String userMail){

        userService.deleteUser(userMail);
        return ResponseEntity.ok("User Delete Successfully");
    }

    @Operation(summary = "Get Users By Skills Only ADMIN")
    @GetMapping("/getUsersBySkill/{userSkill}")
    public ResponseEntity<Page<GetUserDTO>> getUsersBySkills(Authentication authentication,@RequestParam (defaultValue = "0")int page,@RequestParam (defaultValue = "3") int size,@PathVariable String userSkill){
        String userMail=authentication.getName();
        Pageable pageable=PageRequest.of(page,size);
        return new ResponseEntity<>(userService.getUserBySkill(userMail,userSkill,pageable),HttpStatus.OK);
    }

    @Operation(summary = "Get userId Internally")
    @GetMapping("/internal/{userId}")
    public ResponseEntity<FeignUserDTO> getInternalUserRole(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.getInternalUserRole(userId),HttpStatus.OK);
    }


}
