package com.sparta.be.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    // User Entity객체가 DB에 저장될때 생성되는 고유 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //유저 ID
    @Column(nullable = false)
    private String loginId;

    //유저 PW
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Builder
    public User(String loginId, String password, String nickName){
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickName;
    }
}
