package com.example.demo.service;

import com.example.demo.domain.Schedule;
import com.example.demo.domain.User;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;  // 올바른 경로


@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public Schedule createSchedule(Long userId, String title, String content) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Schedule schedule = Schedule.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
        return scheduleRepository.save(schedule);
    }

    public Schedule getSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule updateSchedule(Long id, String title, String content, Long userId) {
        Schedule schedule = getSchedule(id);
        // 본인 것이 맞는지 검증 로직 (생략 가능)
        if(!schedule.getUser().getId().equals(userId)) {
            throw new SecurityException("Not authorized to update this schedule");
        }
        schedule.setTitle(title);
        schedule.setContent(content);
        return schedule;
    }

    public void deleteSchedule(Long id, Long userId) {
        Schedule schedule = getSchedule(id);
        if(!schedule.getUser().getId().equals(userId)) {
            throw new SecurityException("Not authorized to delete this schedule");
        }
        scheduleRepository.delete(schedule);
    }
}
