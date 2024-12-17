package ru.glazunov.numberDetectionJava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.testng.annotations.Test;

import java.io.IOException;
import org.mockito.InjectMocks;
import ru.glazunov.numberDetectionJava.exception.NoFileException;


import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

@RequiredArgsConstructor
@SpringBootTest
public class UploadImageServiceTest {

    @InjectMocks
    private UploadImageService uploadImageService;


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



    @Test
    public void testUploadImage_noFileAndNoUrl_shouldThrowNoFileException() {

        // Мокаем MultipartFile
        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(true); // Говорим, что файл пустой

        // Пустой URL
        String url = "";

        // Мокаем модель
        Model model = mock(Model.class);

        // Создаем экземпляр реального сервиса
        UploadImageService uploadImageService = new UploadImageService();

        // Проверяем, что выбрасывается исключение NoFileException
        assertThrows(NoFileException.class, () -> {
            uploadImageService.uploadImage(file, url, model);  // Вызываем метод
        });
    }

}