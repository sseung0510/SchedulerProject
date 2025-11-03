package com.example.schedulerproject.service;

import com.example.schedulerproject.dto.request.CreateScheduleRequest;
import com.example.schedulerproject.dto.response.CreateScheduleResponse;
import com.example.schedulerproject.entity.Schedule;
import com.example.schedulerproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository; // 레포지토리 호출

    // lv1 - 일정 생성
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getUserName(),
                request.getUserPwd()
        );
        Schedule saved = repository.save(schedule);
        return new CreateScheduleResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getUserName(),
                saved.getCreatedAt(),
                saved.getModifiedAt()
        );
    }

}
