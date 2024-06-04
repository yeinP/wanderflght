package com.fly.wanderflight.notice.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeDto {
    private long noticeNo;

    private String noticeTitle;

    private String noticeContent;

    private long noticeViewCount;

    private char noticeImportant;

    private long noticeWriter;

    private Timestamp noticeCreateDate;
}
