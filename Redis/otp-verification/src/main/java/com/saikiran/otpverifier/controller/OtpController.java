package com.saikiran.otpverifier.controller;


import com.saikiran.otpverifier.dto.OtpRequest;
import com.saikiran.otpverifier.dto.OtpVerificationRequest;
import com.saikiran.otpverifier.service.otpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/otp")
public class OtpController {
    private final otpService redisService;

    @PostMapping("generate")
    public String generateOtp(@RequestBody OtpRequest otpRequest){

        //generate otp
       String otp =  redisService.generateOtp();

       //storing in redis
        redisService.saveData(redisService.keyGenerator(otpRequest.getPhoneNumber()),otp);

        return otp;
    }


    @PostMapping("/verify")
    public boolean verifyOtp(@RequestBody OtpVerificationRequest otp){
        return redisService.otpVerifier(otp.getPhoneNumber(),otp.getOtp());
    }

    @GetMapping("/health")
    public String health() {
        return "OTP service is running";
    }
}
