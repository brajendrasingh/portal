package com.bksoft.auth.api.models.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupResponse {
    private String status;
    private String message;

    public SignupResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
