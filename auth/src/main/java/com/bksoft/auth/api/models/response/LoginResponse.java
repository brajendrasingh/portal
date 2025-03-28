package com.bksoft.auth.api.models.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String status;
    private String token;
    private String message;

    public LoginResponse(String status, String token, String message) {
        this.status = status;
        this.token = token;
        this.message = message;
    }
}
