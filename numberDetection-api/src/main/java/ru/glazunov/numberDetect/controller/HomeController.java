package ru.glazunov.numberDetect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public interface HomeController {

    @GetMapping("/")
    String home();

}
