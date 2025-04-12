package com.bksoft.auth.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String profileImage;
    private String role;
}
