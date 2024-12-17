package ru.glazunov.numberDetectionJava.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.glazunov.numberDetect.controller.DetectionController;
import ru.glazunov.numberDetectionJava.service.UploadImageService;
import org.springframework.http.HttpHeaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@RequiredArgsConstructor
@Controller
public class DetectionControllerImpl implements DetectionController {

    private final UploadImageService uploadImageService;
    private final HttpServletRequest request;

    @Override
    public ResponseEntity<byte[]> uploadImage(MultipartFile file, String url, Model model) throws IOException, InterruptedException {

        if (file.isEmpty()) {
            model.addAttribute("message", "Файл не выбран");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Возвращаем ошибку, если файл не был выбран
        }

        uploadImageService.uploadImage(file, url, model);

        File imageFile = new File("output.png");

        if (!imageFile.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Возвращаем ошибку 404, если файл не найден
        }

        byte[] fileContent = readFileToByteArray(imageFile);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + imageFile.getName());
        headers.add("Content-Type", "application/octet-stream");

        // Отправляем файл в ответе
        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }

    // Метод для чтения файла в байты
    private byte[] readFileToByteArray(File file) throws IOException {
        try (InputStream is = new FileInputStream(file)) {
            byte[] fileContent = new byte[(int) file.length()];
            is.read(fileContent);
            return fileContent;
        }
    }
}
