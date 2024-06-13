package com.fly.wanderflight.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name="USERS")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userNo;

    private String userId;

    private  String userEmail;

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

    @PrePersist
    public void prePersist() {
        this.userRegDate = Timestamp.from(Instant.now());
    }


}
