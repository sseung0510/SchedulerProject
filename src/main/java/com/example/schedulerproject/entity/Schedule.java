package com.example.schedulerproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userPwd;

    public Schedule(String title, String content, String userName, String userPwd) {
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.userPwd = userPwd;
    }

//    public void update(String title, String content, String userName, String userPwd) {
//        this.title = title;
//        this.content = content;
//        this.userName = userName;
//        this.userPwd = userPwd;
//    }

}
