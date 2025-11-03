package com.example.schedulerproject.service;

import com.example.schedulerproject.dto.request.CreateScheduleRequest;
import com.example.schedulerproject.dto.response.CreateScheduleResponse;
import com.example.schedulerproject.dto.response.GetScheduleResponse;
import com.example.schedulerproject.entity.Schedule;
import com.example.schedulerproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    // lv2 - 전체 일정 조회(작성자명)
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAll(String userName) {
        List<Schedule> schedules;

        if(userName == null) {
            schedules = repository.findAll();
        } else {
            schedules = repository.findByUserName(userName);
        }

        List<GetScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getUserName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }

        return dtos.stream().sorted(Comparator.comparing(GetScheduleResponse::getModifiedAt).reversed()).collect(Collectors.toList());
    }

    // lv2 - 일정 조회(id값)
    @Transactional(readOnly = true)
    public GetScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = repository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("일정이 없습니다.")
        );
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUserName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
