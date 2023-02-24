package com.sparta.be.entity;

import com.sparta.be.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity(name = "users")
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
    private String nickName;

    public User(UserRequestDto userRequestDto){
        this.loginId = userRequestDto.getLoginId();
        this.password = userRequestDto.getPassword();
        this.nickName = userRequestDto.getNickName();
    }
}
