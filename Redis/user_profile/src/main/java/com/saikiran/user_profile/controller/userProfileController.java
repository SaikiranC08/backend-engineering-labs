package com.saikiran.user_profile.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saikiran.user_profile.dto.UserProfileSet;
import com.saikiran.user_profile.service.userProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class userProfileController {

    private final userProfileService service;

    // VALUE OPERATIONS

    @PostMapping("/value/{userId}")
    public String setUserProfile(
            @RequestBody UserProfileSet user,
            @PathVariable String userId
    ) throws JsonProcessingException {

        service.setUserProfile(user, userId);

        return "User stored using value";
    }

    @GetMapping("/value/{userId}")
    public UserProfileSet getUserProfile(
            @PathVariable String userId
    ) throws JsonProcessingException {

        return service.getUserProfile(userId);
    }


    // HASH OPERATIONS

    @PostMapping("/hash/{userId}")
    public String setUserProfileHash(
            @RequestBody UserProfileSet user,
            @PathVariable String userId
    ) {

        service.setUserProfileHash(user, userId);

        return "User stored using hash";
    }

    @GetMapping("/hash/{userId}")
    public UserProfileSet getUserProfileHash(
            @PathVariable String userId
    ) {

        return service.getUserProfileHash(userId);
    }
}
