package com.bksoft.auth.api.controller;

import com.bksoft.auth.api.models.request.SignupRequest;
import com.bksoft.auth.api.models.response.SignupResponse;
import com.bksoft.auth.api.models.response.UserProfileResponse;
import com.bksoft.auth.dtos.UserDto;
import com.bksoft.auth.services.UserService;
import com.bksoft.auth.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private transient UserService userService;
    private final JwtUtils jwtUtils;

    public UserController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUser() {
        List<UserProfileResponse> response = new ArrayList<>();
        for (UserDto user : userService.findAll()) {
            response.add(new UserProfileResponse(user.getEmail(), "", user.getUsername(), user.getEmail(), "./../../assets/images/logo.png", user.getRole()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String token, String username) {
        String tokenUserName = jwtUtils.extractUsername(token.substring(7));
        UserDto userInfo = userService.findByEmail(tokenUserName).get();
        return ResponseEntity.ok(new UserProfileResponse(userInfo.getEmail(), "", userInfo.getUsername(), userInfo.getEmail(), "./../../assets/images/logo.png", userInfo.getRole()));
    }

    @GetMapping("/userDetail")
    public ResponseEntity<?> getUserDetail(@RequestHeader("Authorization") String token, String username) {
        UserDto userInfo = userService.findByEmail(username).get();
        return ResponseEntity.ok(new UserProfileResponse(userInfo.getEmail(), "", userInfo.getEmail(), userInfo.getEmail(), "./../../assets/images/logo.png", userInfo.getRole()));
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody List<SignupRequest> addUserRequest) {
        List<UserDto> response = new ArrayList<>();
        for (SignupRequest request : addUserRequest) {
            UserDto user = new UserDto();
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            response.add(userService.registerUser(user));
        }
        return ResponseEntity.ok(new SignupResponse("success", response.stream().map(UserDto::getEmail)
                .collect(Collectors.joining(", ")) + " user added successfully"));
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody List<SignupRequest> updateUserRequest) {
        List<UserDto> response = new ArrayList<>();
        for (SignupRequest request : updateUserRequest) {
            UserDto user = new UserDto();
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            response.add(userService.update(user));
        }
        return ResponseEntity.ok(new SignupResponse("success", response.stream().map(UserDto::getEmail)
                .collect(Collectors.joining(", ")) + " user updated successfully"));
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody List<String> userNameList) {
        for (String userName : userNameList) {
            userService.deleteByEmail(userName);
        }
        return ResponseEntity.ok(new SignupResponse("success", userNameList.stream().map(String::toString)
                .collect(Collectors.joining(", ")) + " user deleted successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> updates) {
         String username = jwtUtils.extractUsername(token.substring(7));
        UserDto existingUser = userService.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        if (updates.containsKey("email")) {
            existingUser.setEmail(updates.get("email"));
        }
        if (updates.containsKey("password") && !updates.get("password").isEmpty()) {
            existingUser.setPassword(updates.get("password"));
        }
        userService.updateUser(existingUser);
        return ResponseEntity.ok("Profile updated successfully");
    }

}
