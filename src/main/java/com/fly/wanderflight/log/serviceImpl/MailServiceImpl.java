package com.fly.wanderflight.log.serviceImpl;

import com.fly.wanderflight.log.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public boolean mailSend(String subject, String text, String from, String to, String filePath) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
        }catch (MessagingException e){
            e.printStackTrace();
        }
        return false;
    }
}
