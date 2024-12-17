package ru.glazunov.numberDetectionJava.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.glazunov.numberDetectionJava.exception.NoFileException;
import ru.glazunov.numberDetectionJava.exception.TooMuchFiles;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NoFileException.class)
    public ModelAndView handleNoFileException(final NoFileException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(TooMuchFiles.class)
    public ModelAndView handleTooMuchFilesException(final TooMuchFiles e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", e.getMessage());
        return modelAndView;
    }

}
