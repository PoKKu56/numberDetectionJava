package ru.glazunov.numberDetectionJava.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.glazunov.numberDetect.controller.ErrorController;
import ru.glazunov.numberDetectionJava.exception.NoFileException;

@RequiredArgsConstructor
@Controller
public class ErrorControllerImpl implements ErrorController {
    @Override
    public String error() {
        throw new NoFileException("Отсутствует файл");
    }
}
