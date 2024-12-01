package ru.glazunov.numberDetect.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.glazunov.numberDetect.dto.UploadImageRequest;
import ru.glazunov.numberDetect.dto.UploadImageResponse;

@RequestMapping("/number-detect")
public interface DetectionController {

    @PostMapping("/upload")
    UploadImageResponse uploadImage(UploadImageRequest uploadImageRequest);
}
