package ru.glazunov.numberDetectionJava.service;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import org.springframework.mock.web.MockMultipartFile;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.glazunov.numberDetect.dto.UploadImageResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import org.mockito.InjectMocks;

import static javax.management.Query.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;



public class UploadImageServiceTest {

    @InjectMocks
    private final UploadImageService uploadImageService = new UploadImageService();


    @Test
    void testImageToByte64() throws IOException {
        // Подготовка mock файла
        byte[] imageContent = "test image content".getBytes();
        MockMultipartFile file = new MockMultipartFile("file", "test.png", "image/png", imageContent);

        // Вызов метода
        String base64Image = uploadImageService.imageToByte64(file);

        // Проверка
        assertNotNull(base64Image);
        assertFalse(base64Image.isEmpty(), "Base64 string should not be empty");
    }

}