package ru.glazunov.numberDetect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/numberDetect")
public interface NumberDetectContoller {

    @GetMapping("/main")
    public String redirectToMainPage();

    @GetMapping("/devs")
    public String redirectToDevPage();

    @GetMapping("/help")
    public String redirectToHelpPage();

    @GetMapping("/results")
    public String redirectToResultsPage();

}
