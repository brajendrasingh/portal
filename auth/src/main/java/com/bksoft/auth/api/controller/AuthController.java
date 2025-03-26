package com.bksoft.auth.api.controller;

import com.bksoft.auth.api.models.request.LoginRequest;
import com.bksoft.auth.api.models.request.SignupRequest;
import com.bksoft.auth.api.models.response.LoginResponse;
import com.bksoft.auth.api.models.response.SignupResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(new LoginResponse("success", "token", "Login successful"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest request) {

        return ResponseEntity.ok(new SignupResponse("success","User registered successfully"));
    }
}


