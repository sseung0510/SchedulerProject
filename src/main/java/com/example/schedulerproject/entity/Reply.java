package com.example.schedulerproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "replies")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Column(length = 100, nullable = false)
    private String replyContent;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY) // many = reply, one = schedule / 하나의 일정에 여러개의 댓글 fetch 찾아보기
    @JoinColumn(name="scheduleId")
    private Schedule schedule;

    public Reply(String replyContent, String userName, String password) {
        this.replyContent = replyContent;
        this.userName = userName;
        this.password = password;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}