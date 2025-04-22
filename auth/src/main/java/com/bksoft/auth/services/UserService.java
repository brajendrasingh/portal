package com.bksoft.auth.services;

import com.bksoft.auth.dtos.UserDto;
import com.bksoft.auth.entities.UserEntity;
import com.bksoft.auth.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto registerUser(UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getEmail());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        UserEntity response = userRepository.save(userEntity);
        return new UserDto(response.getUsername(), response.getEmail(), response.getPassword(), userEntity.getRole());
    }

    public Optional<UserDto> findByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(email);
        return Optional.of(new UserDto(userEntity.get().getUsername(), userEntity.get().getEmail(), userEntity.get().getPassword(), userEntity.get().getRole()));
    }

    public List<UserDto> findAll() {
        List<UserDto> userList = new ArrayList<>();
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity userEntity : users) {
            userList.add(new UserDto(userEntity.getUsername(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getRole()));
        }
        return userList;
    }

    public UserDto update(UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        UserEntity response = userRepository.save(userEntity);
        return new UserDto(response.getUsername(), response.getEmail(), response.getPassword(), userEntity.getRole());
    }

    public boolean deleteByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(email);
        userRepository.deleteById(userEntity.get().getId());
        return true;
    }

    public boolean updateUser(UserDto user) {
        UserEntity existingUser = userRepository.findByEmail(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        int response = userRepository.updateEmailByUserId(existingUser.getId(), user.getEmail());
        return response > 0 ? true : false;
    }

}
