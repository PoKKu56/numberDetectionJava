package ru.glazunov.numberDetectionJava.controller;

import org.springframework.stereotype.Controller;
import ru.glazunov.numberDetect.controller.NumberDetectContoller;

@Controller
public class NumberDetectionControllerImpl implements NumberDetectContoller {
    @Override
    public String redirectToDevPage() {
        return "numberDetect/devs";
    }

    @Override
    public String redirectToHelpPage() {
        return "numberDetect/help";
    }

    @Override
    public String redirectToResultsPage() {
        return "numberDetect/results";
    }

    @Override
    public String redirectToMainPage() {
        return "numberDetect/main";
    }
}
