package com.fly.wanderflight.log.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDto {
    private long userNo;

    private String userId;

    private String userEmail;

    private String userPassword;

    private long userBirth;

    private long userPhone;

    private char userGender;

    private long userGrade;

    private String userKorLastname;
    private String userKorFirstname;
    private String userEngLastname;
    private String userEngFirstname;


    private Timestamp userRegDate;

}
