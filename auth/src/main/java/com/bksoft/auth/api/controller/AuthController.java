package com.bksoft.auth.api.controller;

import com.bksoft.auth.api.models.request.LoginRequest;
import com.bksoft.auth.api.models.request.SignupRequest;
import com.bksoft.auth.api.models.response.LoginResponse;
import com.bksoft.auth.api.models.response.SignupResponse;
import com.bksoft.auth.dtos.UserDto;
import com.bksoft.auth.services.UserService;
import com.bksoft.auth.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    public AuthController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            final UserDto userDetails = userService.findByEmail(request.getEmail()).orElseThrow();
            final String token = jwtUtils.generateToken(userDetails.getEmail());
            return ResponseEntity.ok(new LoginResponse("success", token, "Login successful"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest request) {
        UserDto user = new UserDto();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        UserDto response = userService.registerUser(user);
        return ResponseEntity.ok(new SignupResponse("success", response.getEmail() + " user registered successfully"));
    }
}


