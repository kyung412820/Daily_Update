package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public User createUser(String username, String email, String password) {
        User user = User.builder()
                .username(username)
                .email(email)
                .password(password) // TODO: 비밀번호 암호화 (도전 기능)
                .build();
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, String username, String email) {
        User user = getUser(id);
        user.setUsername(username);
        user.setEmail(email);
        return user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
