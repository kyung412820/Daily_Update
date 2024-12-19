package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mvc/users") // URL을 "/mvc/users"로 변경
public class UserController {

    private final UserService userService;

    // 사용자 생성
    @PostMapping("/save")
    public String createUser(@RequestParam String username,
                             @RequestParam String email,
                             @RequestParam String password,
                             Model model) {
        User user = userService.createUser(username, email, password);
        model.addAttribute("user", user); // 생성된 사용자 정보 전달
        return "redirect:/mvc/users/list"; // 생성 후 목록 페이지로 리다이렉트
    }

    // 사용자 목록 조회
    @GetMapping("/list")
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users); // 사용자 목록을 모델에 추가
        return "user-list"; // user-list.jsp 반환
    }

    // 사용자 단일 조회
    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user-details"; // user-details.jsp 반환
    }

    // 사용자 수정
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id,
                             @RequestParam String username,
                             @RequestParam String email,
                             HttpServletRequest request) {
        checkOwnership(id, request);
        userService.updateUser(id, username, email);
        return "redirect:/mvc/users/list"; // 수정 후 목록 페이지로 리다이렉트
    }

    // 사용자 삭제
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, HttpServletRequest request) {
        checkOwnership(id, request);
        userService.deleteUser(id);
        return "redirect:/mvc/users/list"; // 삭제 후 목록 페이지로 리다이렉트
    }

    // 소유권 검사 (세션 기반)
    private void checkOwnership(Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long sessionUserId = (Long) session.getAttribute("userId");
        if (!id.equals(sessionUserId)) {
            throw new SecurityException("Not authorized to modify this user");
        }
    }
}
