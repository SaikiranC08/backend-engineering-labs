package com.saikiran.user_profile.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserProfileSet {
    String userName;
    String status;
    String role;
}
