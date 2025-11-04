package com.example.schedulerproject.controller;

import com.example.schedulerproject.dto.request.CreateScheduleRequest;
import com.example.schedulerproject.dto.request.DeleteScheduleRequest;
import com.example.schedulerproject.dto.request.UpdateScheduleRequest;
import com.example.schedulerproject.dto.response.CreateScheduleResponse;
import com.example.schedulerproject.dto.response.GetScheduleResponse;
import com.example.schedulerproject.dto.response.UpdateScheduleResponse;
import com.example.schedulerproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    // lv1 - 일정 생성
    @PostMapping("/scheduler")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    // lv2 - 전체 일정 조회(작성자명)
    @GetMapping("/scheduler")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedules(@RequestParam(required = false) String userName) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(userName));
    }

    // lv2 - 선택 일정 조회(id)
    @GetMapping("/scheduler/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findOne(scheduleId));
    }

    // lv3 - 일정 수정
    @PutMapping("/scheduler/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(scheduleId, request));
    }

    // lv4 - 일정 삭제
    @DeleteMapping("/scheduler/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestBody DeleteScheduleRequest request
    ) {
        service.delete(scheduleId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
