package com.fly.wanderflight.notice.serviceImpl;



import com.fly.wanderflight.entity.Notice;
import com.fly.wanderflight.notice.dto.NoticeDto;
import com.fly.wanderflight.notice.repository.NoticeRepository;
import com.fly.wanderflight.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeRepository noticeRepository;

    private int size = 10;
    @Override
    public int uploadNotice(NoticeDto noticeDto) {
        Notice notice = noticeDtoToEntity(noticeDto);
        noticeRepository.save(notice);
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NoticeDto> noticeList(Pageable pageable) {
        Page<Notice> noticePage = noticeRepository.findAll(pageable);
        Page<NoticeDto> noticeDtoPage = noticePage.map(this::noticeEntityToDto);
        return noticeDtoPage;
    }

    @Override
    public NoticeDto getNoticeNo(long noticeNo) {
        try {
            Notice notice = noticeRepository.findById(noticeNo).orElseThrow(NoSuchElementException::new);
            return noticeEntityToDto(notice);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "공지가 없습니다", e);
        }
    }

    @Override
    public void noticeModify(NoticeDto noticeDto) {
        Notice notice = noticeDtoToEntity(noticeDto);
        noticeRepository.save(notice);
    }

    @Override
    @Transactional
    public void deleteNotice(Long noticeNo) {
        noticeRepository.deleteById(noticeNo);
    }


}
