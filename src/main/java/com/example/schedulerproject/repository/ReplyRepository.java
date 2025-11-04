package com.example.schedulerproject.repository;

import com.example.schedulerproject.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Long countByScheduleId(Long scheduleId);
}
