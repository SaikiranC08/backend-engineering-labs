package com.saikiran.otpverifier.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OtpVerificationRequest {
    String phoneNumber;
    String otp;
}
