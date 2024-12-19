package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mvc/posts")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password, HttpServletRequest request) {
        User user = authService.login(email, password);
        HttpSession session = request.getSession(true);
        session.setAttribute("userId", user.getId());
        return user;
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
    }
}
