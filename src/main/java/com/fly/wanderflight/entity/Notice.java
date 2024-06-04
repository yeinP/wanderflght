package com.fly.wanderflight.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name="notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeNo;

    private String noticeTitle;

    private String noticeContent;

    private long noticeViewCount;

    private char noticeImportant;

    private long noticeWriter = 1;

    private Timestamp noticeCreateDate;

    @PrePersist
    public void prePersist() {
        this.noticeCreateDate = Timestamp.from(Instant.now());
    }


}
