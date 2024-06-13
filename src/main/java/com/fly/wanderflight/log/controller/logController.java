package com.fly.wanderflight.log.controller;

import com.fly.wanderflight.common.DuplicateException;
import com.fly.wanderflight.log.dto.UsersDto;
import com.fly.wanderflight.log.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class logController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getLogin(){
        return "/log/login";
    }

    @PostMapping("/login")
    public String postLogin(){
        return "redirect:/";
    }

    @GetMapping("/register2")
    public String register2(){
        return "/log/register2";
    }

    @PostMapping("/register2/duplicateEmail")
    @ResponseBody
    public ResponseEntity<Boolean> validateDuplicateUserEmail(@RequestBody String userEmail) {
        try {
            userService.validateDuplicateUserEmail(userEmail);
            return ResponseEntity.ok(false); // 중복 아님
        } catch (DuplicateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(true); // 중복임
        }
    }

    @PostMapping("/register2/duplicateId")
    @ResponseBody
    public ResponseEntity<String> validateDuplicateUserId(@RequestBody String userId) {
        try {
            userService.validateDuplicateUserEmail(userId);
            return ResponseEntity.ok("false"); // 중복 아님
        } catch (DuplicateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("true"); // 중복임
        }
    }

    @PostMapping("/register")
    public String registerPost(UsersDto usersDto){
        userService.registerUser(usersDto);
        return "redirect:/";
    }

    @GetMapping("/findId")
    public String findId(){
        return "/log/findId";
    }

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response) throws IOException {
        int width = 150;
        int height = 50;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        Random random = new Random();
        Font font = new Font("Georgia", Font.BOLD, 18);
        g2d.setFont(font);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(Color.BLACK);
        String captcha = String.valueOf(random.nextInt(99999));
        g2d.drawString(captcha, 25, 25);

        g2d.dispose();

        response.setContentType("image/png");
        ImageIO.write(bufferedImage, "png", response.getOutputStream());
    }
}
