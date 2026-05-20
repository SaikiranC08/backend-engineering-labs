package com.saikiran.otpverifier.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class otpService {

    private final StringRedisTemplate stringRedisTemplate;
    private final SecureRandom secureRandom;


    public void saveData(String key,String value){
        stringRedisTemplate.opsForValue().set(key,value,120, TimeUnit.SECONDS);
    }
    public String getData(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public String keyGenerator(String value){
        return "otp:" +value;
    }



    public String generateOtp() {

        int otp = 100000 + secureRandom.nextInt(900000);

        return String.valueOf(otp);
    }


    public boolean otpVerifier(String phoneNumb,String Otp){

        String redisKey = keyGenerator(phoneNumb);

        String storedOtp = stringRedisTemplate.opsForValue().get(redisKey);

        if (storedOtp == null) {
            return false;
        }

        return storedOtp.equals(Otp);
    }
}
