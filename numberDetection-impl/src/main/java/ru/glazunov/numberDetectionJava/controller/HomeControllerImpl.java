package ru.glazunov.numberDetectionJava.controller;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Controller;
import ru.glazunov.numberDetect.controller.HomeController;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class HomeControllerImpl implements HomeController {
    @Override
    public String home(HttpServletResponse response) {

        String userId = UUID.randomUUID().toString();

        Cookie cookie = new Cookie("number_detection_user_id", userId);

        cookie.setMaxAge(10 * 60);
        cookie.setPath("/");

        response.addCookie(cookie);

        return "redirect:/numberDetect/main";
    }
}
