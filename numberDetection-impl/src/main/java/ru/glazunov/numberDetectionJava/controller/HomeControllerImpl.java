package ru.glazunov.numberDetectionJava.controller;

import org.springframework.stereotype.Controller;
import ru.glazunov.numberDetect.controller.HomeController;

@Controller
public class HomeControllerImpl implements HomeController {
    @Override
    public String home() {
        return "redirect:/numberDetect/main";
    }
}
