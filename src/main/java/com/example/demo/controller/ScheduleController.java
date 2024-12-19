package com.example.demo.controller;

import com.example.demo.domain.Schedule;
import com.example.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mvc/posts") // URL을 /mvc/posts로 설정
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("/save")
    public String createSchedule(@RequestParam String title,
                                 @RequestParam String content,
                                 HttpServletRequest request,
                                 Model model) {
        Long userId = getUserIdFromSession(request);
        Schedule schedule = scheduleService.createSchedule(userId, title, content);
        model.addAttribute("schedule", schedule);
        return "redirect:/mvc/posts/list"; // 저장 후 목록 페이지로 리다이렉트
    }

    // 특정 일정 조회
    @GetMapping("/{id}")
    public String getSchedule(@PathVariable Long id, Model model) {
        Schedule schedule = scheduleService.getSchedule(id);
        model.addAttribute("schedule", schedule);
        return "schedule-details"; // JSP 페이지 반환
    }

    // 전체 일정 목록
    @GetMapping("/list")
    public String getAllSchedules(Model model) {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        model.addAttribute("schedules", schedules);
        return "schedule-list"; // JSP 페이지 반환
    }

    // 일정 수정
    @PostMapping("/update/{id}")
    public String updateSchedule(@PathVariable Long id,
                                 @RequestParam String title,
                                 @RequestParam String content,
                                 HttpServletRequest request) {
        Long userId = getUserIdFromSession(request);
        scheduleService.updateSchedule(id, title, content, userId);
        return "redirect:/mvc/posts/list"; // 수정 후 목록 페이지로 리다이렉트
    }

    // 일정 삭제
    @PostMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id, HttpServletRequest request) {
        Long userId = getUserIdFromSession(request);
        scheduleService.deleteSchedule(id, userId);
        return "redirect:/mvc/posts/list"; // 삭제 후 목록 페이지로 리다이렉트
    }

    private Long getUserIdFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (Long) session.getAttribute("userId");
    }
}
