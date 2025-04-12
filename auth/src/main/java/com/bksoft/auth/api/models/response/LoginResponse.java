package com.bksoft.auth.api.models.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String userName;
    private String status;
    private String accessToken;
    private String refreshToken;
    private String message;
    private String errorCode;


    public LoginResponse(String status, String accessToken, String refreshToken, String message) {
        this.status = status;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.message = message;
    }
}
