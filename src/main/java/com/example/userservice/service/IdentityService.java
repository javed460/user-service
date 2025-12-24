package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IdentityService {

    private final UserRepository userRepository;

    public Optional<UserDto> findUser(String username) {
        return userRepository.findById(username)
                .map(user -> UserDto.builder()
                        .id(user.getUsername())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .build());
    }

    public boolean validateCredentials(String username, String password) {
        return userRepository.findById(username)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
}