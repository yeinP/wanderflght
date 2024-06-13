package com.fly.wanderflight.log.service;

public interface MailService {
    boolean mailSend(String subject, String text, String from, String to, String filePath);
}
