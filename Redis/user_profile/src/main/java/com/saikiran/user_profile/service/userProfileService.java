package com.saikiran.user_profile.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saikiran.user_profile.dto.UserProfileSet;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class userProfileService {

    private final StringRedisTemplate stringRedisTemplate;
    ObjectMapper objectMapper;

    {
        new ObjectMapper();
    }


    //key name generation
    public String keyGeneration(String id){
        return "userid"+id+"json";
    }



    //using the normal .set method for data storing and getting .get()

    public void setUserProfile(UserProfileSet user,String userId) throws JsonProcessingException {
        String redisKey = keyGeneration(userId);

        String value = objectMapper.writeValueAsString(user);

        stringRedisTemplate.opsForValue().set(redisKey,value);
    }

    public UserProfileSet getUserProfile(String userId) throws JsonProcessingException {
        String redisKey = keyGeneration(userId);
        String data = stringRedisTemplate.opsForValue().get(redisKey);
        UserProfileSet user = objectMapper.readValue(data, UserProfileSet.class);
        return user;
    }



    //storing the data using hash and get the data

    public void setUserProfileHash(
            UserProfileSet user,
            String userId) {

        try {

            String redisKey = keyGeneration(userId);

            String json =
                    objectMapper.writeValueAsString(user);

            stringRedisTemplate
                    .opsForHash()
                    .put(redisKey, "profile", json);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UserProfileSet getUserProfileHash(String userId) {

        try {

            String redisKey = keyGeneration(userId);

            Object data =
                    stringRedisTemplate
                            .opsForHash()
                            .get(redisKey, "profile");

            if (data == null) {
                return null;
            }

            return objectMapper.readValue(
                    data.toString(),
                    UserProfileSet.class
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
