package com.bksoft.auth.services;

import com.bksoft.auth.dtos.UserDto;
import com.bksoft.auth.entities.UserEntity;
import com.bksoft.auth.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        return new UserDto(response.getEmail(), response.getPassword());
    }

    public Optional<UserDto> findByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return Optional.of(new UserDto(userEntity.get().getEmail(), userEntity.get().getPassword()));
    }
}
