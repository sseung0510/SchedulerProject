package com.example.schedulerproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "replies")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Column(nullable = false)
    private String replyContent;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String replyPwd;

    @ManyToOne // many = reply, one = schedule / 하나의 일정에 여러개의 댓글
    @JoinColumn(name="scheduleId")
    private Schedule schedule;

    public Reply(String replyContent, String userName, String replyPwd) {
        this.replyContent = replyContent;
        this.userName = userName;
        this.replyPwd = replyPwd;
    }
}
