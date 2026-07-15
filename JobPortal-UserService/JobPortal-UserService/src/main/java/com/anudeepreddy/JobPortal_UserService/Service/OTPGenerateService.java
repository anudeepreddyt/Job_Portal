package com.anudeepreddy.JobPortal_UserService.Service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;


@Service
public class OTPGenerateService {
    private final static SecureRandom random=new SecureRandom();
    public static String generateOTP(){
        int otp=100000+random.nextInt(900000);
        return String.valueOf(otp);
    }
}
