package com.bksoft.auth.api.controller;

import com.bksoft.auth.dtos.UserDto;
import com.bksoft.auth.services.UserService;
import com.bksoft.auth.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/token")
public class TokenController {
    private final JwtUtils jwtUtils;
    private final UserService userService;

    public TokenController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refresh_token");

        if (refreshToken == null || jwtUtils.isTokenExpired(refreshToken)) {
            return ResponseEntity.status(403).body(Map.of("error", "Invalid or expired refresh token"));
        }

        String username = jwtUtils.extractUsername(refreshToken);
        UserDto user = userService.findByEmail(username).orElseThrow();
        String newAccessToken = jwtUtils.generateAccessToken(user.getEmail());

        return ResponseEntity.ok(Map.of("access_token", newAccessToken));
    }
}
