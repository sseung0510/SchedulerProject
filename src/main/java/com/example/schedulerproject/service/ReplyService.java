package com.example.schedulerproject.service;

import com.example.schedulerproject.dto.request.CreateReplyRequest;
import com.example.schedulerproject.dto.response.CreateReplyResponse;
import com.example.schedulerproject.entity.Reply;
import com.example.schedulerproject.entity.Schedule;
import com.example.schedulerproject.repository.ReplyRepository;
import com.example.schedulerproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ScheduleRepository scheduleRepository; // 레포지토리 호출
    private final ReplyRepository replyRepository;

    // lv5 - 댓글 생성
    @Transactional
    public CreateReplyResponse saveReply(Long scheduleId, CreateReplyRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정이 없습니다.")
        );

        if(replyRepository.countByScheduleId(scheduleId) >= 10) { // 하나의 일정에 댓글이 10개이상 존재하면 오류 반환
            throw new ResponseStatusException(HttpStatus.CONFLICT, "댓글이 10개 이상 존재합니다.");
        }

        Reply reply = new Reply(
                request.getReplyContent(),
                request.getUserName(),
                request.getPassword()
        );

        reply.setSchedule(schedule);

        Reply saved = replyRepository.save(reply);
        return new CreateReplyResponse(
                saved.getReplyId(),
                saved.getReplyContent(),
                saved.getUserName(),
                saved.getCreatedAt(),
                saved.getModifiedAt()
        );
    }
}
