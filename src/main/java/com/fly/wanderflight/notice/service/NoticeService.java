package com.fly.wanderflight.notice.service;



import com.fly.wanderflight.entity.Notice;
import com.fly.wanderflight.notice.dto.NoticeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface NoticeService {
    int uploadNotice(NoticeDto notice);


    Page<NoticeDto> noticeList(Pageable pageable);

    NoticeDto getNoticeNo(long noticeNo);

    void noticeModify(NoticeDto noticeDto);

    void deleteNotice(Long noticeNo);




    default NoticeDto noticeEntityToDto(Notice notice){
        NoticeDto dto = NoticeDto.builder().noticeNo(notice.getNoticeNo())
                .noticeContent(notice.getNoticeContent())
                .noticeTitle(notice.getNoticeTitle())
                .noticeViewCount(notice.getNoticeViewCount())
                .noticeImportant(notice.getNoticeImportant())
                .noticeWriter(notice.getNoticeWriter())
                .noticeCreateDate(notice.getNoticeCreateDate()).build();
        return dto;
    }

    default Notice noticeDtoToEntity(NoticeDto noticeDto){
        Notice notice = Notice.builder().noticeNo(noticeDto.getNoticeNo())
                        .noticeTitle(noticeDto.getNoticeTitle())
                        .noticeContent(noticeDto.getNoticeContent())
                        .noticeViewCount(noticeDto.getNoticeViewCount())
                        .noticeImportant(noticeDto.getNoticeImportant())
                        .noticeWriter(noticeDto.getNoticeWriter())
                        .noticeCreateDate(noticeDto.getNoticeCreateDate())
                        .build();
        return notice;
    }


}
