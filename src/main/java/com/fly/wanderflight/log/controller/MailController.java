package com.fly.wanderflight.log.controller;

import com.fly.wanderflight.log.dto.UsersDto;
import com.fly.wanderflight.log.service.MailService;
import com.fly.wanderflight.log.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Random;

@Controller
public class MailController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;


    //회원가입 시 이메일 인증번호 보내는 거
    @RequestMapping(value = "/sendMail/auth", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Boolean sendMailAuth(HttpSession session, @RequestParam String email){
        int randomNum = new Random().nextInt(100000) + 10000; //10000~99999중 랜덤 번호 생성
        String joinCode = String.valueOf(randomNum);
        session.setAttribute("joinCode", joinCode);

        String subject = "[WanderFlight] 회원가입 인증 코드 발급 안내";
        StringBuilder sb = new StringBuilder();
        sb.append("인증코드는 [" + joinCode + "] 입니다.");

        return mailService.mailSend(subject, sb.toString(), "test1031.project@gmail.com", email, null);
    }



    //아이디 찾기
    @RequestMapping(value = "/sendMail/findId", method = RequestMethod.POST)
    public String sendMailFindId(HttpSession session, @RequestParam("userEmail") String email, @RequestParam("captcha") String captcha, RedirectAttributes redirectAttributes){
        String captchaValue = (String) session.getAttribute("captcha");
        if(captchaValue == null || !captchaValue.equals(captcha)){
            redirectAttributes.addFlashAttribute("resultMsg", "방지코드가 일치하지 않습니다");
            return "redirect:log/findId";
        }

        UsersDto usersDto = userService.findEmail(email);
        if(usersDto != null){
            String subject ="[WanderFlight] 아이디 찾기 안내";
            StringBuilder sb = new StringBuilder();
            sb.append("회원님의 아이디는 " + usersDto.getUserId() + " 입니다.");
            mailService.mailSend(subject, sb.toString(), "test1031.project@gmail.com", email, null);
            redirectAttributes.addFlashAttribute("resultMsg", "해당 이메일로 가입된 아이디를 발송하였습니다.");

        }else {
            redirectAttributes.addFlashAttribute("resultMsg", "해당 이메일로 가입된 아이디가 존재하지 않습니다.");

        }
        return "redirect:log/findId";

    }

    //비밀번호 찾기
    @RequestMapping(value = "/sendMail/password", method = RequestMethod.POST)
    public String sendMaillPassword(HttpSession session, @RequestParam("userId") String id, @RequestParam("userEmail") String email, @RequestParam String captcha, RedirectAttributes redirectAttributes){
        String captchaValue = (String) session.getAttribute("captcha");
        if (captchaValue == null || !captchaValue.equals(captcha)) {
            redirectAttributes.addFlashAttribute("resultMsg", "자동 방지 코드가 일치하지 않습니다.");
            return "redirect:log/findPassword";
        }

        UsersDto user = userService.findEmail(email);
        if (user != null) {
            if (!user.getUserId().equals(id)) {
                redirectAttributes.addFlashAttribute("resultMsg", "입력하신 이메일의 회원정보와 가입된 아이디가 일치하지 않습니다.");
                return "redirect:log/findPassword";
            }
            int ran = new Random().nextInt(100000) + 10000;
            String password = String.valueOf(ran);
            userService.updatePassword(user.getUserNo(), "userPassword", password); // 해당 유저의 DB정보 변경

            String subject = "[WanderFlight] 임시 비밀번호 발급 안내";
            StringBuilder sb = new StringBuilder();
            sb.append("임시 비밀번호는 " + password + " 입니다.");
            mailService.mailSend(subject, sb.toString(), "test1031.project@gmail.com", email, null);
            redirectAttributes.addFlashAttribute("resultMsg", "귀하의 이메일 주소로 새로운 임시 비밀번호를 발송 하였습니다.");
        } else {
            redirectAttributes.addFlashAttribute("resultMsg", "귀하의 이메일로 가입된 아이디가 존재하지 않습니다.");
        }
        return "redirect:log/findPassword";
    }
}
