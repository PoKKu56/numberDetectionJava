package ru.glazunov.numberDetect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/numberDetect")
public interface DetectionController {

    @PostMapping("/main")
    ResponseEntity<byte[]> uploadImage(@RequestParam("file") MultipartFile file,
                                       @RequestParam(value = "url", required = false) String url, Model model) throws IOException, InterruptedException;

}
