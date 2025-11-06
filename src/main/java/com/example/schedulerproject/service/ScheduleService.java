package com.example.schedulerproject.service;

import com.example.schedulerproject.dto.request.CreateReplyRequest;
import com.example.schedulerproject.dto.request.CreateScheduleRequest;
import com.example.schedulerproject.dto.request.DeleteScheduleRequest;
import com.example.schedulerproject.dto.request.UpdateScheduleRequest;
import com.example.schedulerproject.dto.response.*;
import com.example.schedulerproject.entity.Reply;
import com.example.schedulerproject.entity.Schedule;
import com.example.schedulerproject.repository.ReplyRepository;
import com.example.schedulerproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository; // 레포지토리 호출
    private final ReplyRepository replyRepository;

    // lv1 - 일정 생성
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getUserName(),
                request.getPassword()
        );
        Schedule saved = scheduleRepository.save(schedule);
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

        if(userName == null) { // 작성자명이 없으면 전체 조회
            schedules = scheduleRepository.findAll();
        } else { // 아니면 작성자명이 동일한 일정만 조회
            schedules = scheduleRepository.findByUserName(userName);
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

    // lv2 - 일정 조회(id값) + lv6 - 일정 단건 조회 업그레이드
    @Transactional(readOnly = true)
    public GetScheduleReplyResponse findOne(Long scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정이 없습니다.")
        );

        List<Reply> replies = replyRepository.findByScheduleId(scheduleId);

        List<GetReplyResponse> dtos = new ArrayList<>();
        for(Reply reply : replies) {
            GetReplyResponse dto = new GetReplyResponse(
                    reply.getReplyId(),
                    reply.getReplyContent(),
                    reply.getUserName(),
                    reply.getCreatedAt(),
                    reply.getModifiedAt()
            );
            dtos.add(dto);
        }

        return new GetScheduleReplyResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUserName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                dtos
        );
    }

    // lv3 - 일정 수정
    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "일정이 없습니다.")
        );

        if((schedule.getPassword()).equals(request.getPassword())) { // 비밀번호가 일치하면 수정
            schedule.updateSchedule(request.getTitle(), request.getUserName());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }

        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getUserName(),
                schedule.getModifiedAt()
        );
    }

    // lv4 - 일정 삭제
    @Transactional
    public void delete(Long scheduleId, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정이 없습니다.")
        );

        if((schedule.getPassword()).equals(request.getPassword())) {
            replyRepository.deleteByScheduleId(scheduleId); // 일정 삭제 전 해당 일정의 id를 가지고 있는 댓글 삭제
            scheduleRepository.deleteById(scheduleId); // 일정 삭제
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }
    }
}