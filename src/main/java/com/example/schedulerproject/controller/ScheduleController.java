package com.example.schedulerproject.controller;

import com.example.schedulerproject.dto.request.CreateScheduleRequest;
import com.example.schedulerproject.dto.response.CreateScheduleResponse;
import com.example.schedulerproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    // lv1 - 일정 생성
    @PostMapping("/scheduler")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }
}
