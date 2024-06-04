package com.fly.wanderflight.notice.controller;



import com.fly.wanderflight.notice.dto.NoticeDto;
import com.fly.wanderflight.notice.repository.NoticeRepository;
import com.fly.wanderflight.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @Autowired
    NoticeRepository noticeRepository;

    @GetMapping("/noticeList")
    public String noticeList(Model model, @PageableDefault(size=10, sort = "noticeCreateDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<NoticeDto> noticePages = noticeService.noticeList(pageable);
        model.addAttribute("noticePage", noticePages);


        return "/notice/noticeList";
    }

    @GetMapping("/noticeContent/{noticeNo}")
    public String noticeContent(Model model, @PathVariable Long noticeNo){
        NoticeDto noticeDto = noticeService.getNoticeNo(noticeNo);
        model.addAttribute("notice", noticeDto);
        return "/notice/noticeContent";
    }
    @GetMapping("/write")
    public String noticeGetWrite(){

        return "/notice/write";
    }

    @PostMapping("/write")
    public String noticePostWrite(NoticeDto noticeDto){
        noticeService.uploadNotice(noticeDto);
        return "redirect:/notice/noticeList";
    }

    @GetMapping("/modify/{noticeNo}")
    public String noticeGetModify(Model model, @PathVariable long noticeNo){
        NoticeDto noticeDto = noticeService.getNoticeNo(noticeNo);
        model.addAttribute("notice", noticeDto);
        return "/notice/modify";
    }

    @PutMapping("/modify/{noticeNo}")
    public String noticeModify(NoticeDto noticeDto){
        noticeService.noticeModify(noticeDto);
        return "redirect:/notice/noticeContent/{noticeNo}";
    }

    @DeleteMapping("/delete/{noticeNo}")
    public String noticeDelete(@PathVariable Long noticeNo){
        noticeService.deleteNotice(noticeNo);
        return "redirect:/notice/noticeList";
    }



}
