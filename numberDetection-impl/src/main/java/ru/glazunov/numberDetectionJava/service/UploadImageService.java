package ru.glazunov.numberDetectionJava.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.glazunov.numberDetect.dto.UploadImageRequest;
import ru.glazunov.numberDetect.dto.UploadImageResponse;
import ru.glazunov.numberDetectionJava.exception.NoFileException;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@RequiredArgsConstructor
@Service
public class UploadImageService {

    private static final String URL = System.getenv("PYTHON_API_URL");
    private final HttpClient client = HttpClient.newHttpClient();

    public void uploadImage(MultipartFile file, String url, Model model) throws IOException, InterruptedException {

        String base64Image = "";

        if (file.isEmpty() && url.isEmpty()) {
            throw new NoFileException("Строка url пуста или Фото не выбрано");
        }

        if (!file.isEmpty()){

            base64Image = imageToByte64(file);

            finalStage(base64Image);

        }

        if (!url.isEmpty()) {

            base64Image = convertImageToBase64(url);

            finalStage(base64Image);

        }

    }

    private void finalStage(String base64Image) throws IOException, InterruptedException {

        UploadImageResponse uploadImageResponse = uploadImageResponse(base64Image);

        saveBase64ImageToFile(uploadImageResponse.getImageBase64(), "output.png");
    }

    public static String convertImageToBase64(String imageUrl) throws IOException {
        // Создаем URL объект
        URL url = new URL(imageUrl);

        // Открываем поток для чтения изображения
        InputStream inputStream = url.openStream();

        // Прочитаем данные из потока в байты
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }

        // Закрываем потоки
        inputStream.close();
        byteArrayOutputStream.close();

        // Получаем байтовый массив изображения
        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        // Преобразуем байтовый массив в строку Base64
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public String imageToByte64(MultipartFile file) throws IOException {
        String base64Image = null;
        try {
            // Преобразование файла в Base64
            byte[] bytes = file.getBytes();
            base64Image = Base64.getEncoder().encodeToString(bytes);

        } catch (IOException e) {

        }
        return base64Image;
    }

    public UploadImageResponse uploadImageResponse(String imageBase64) throws IOException, InterruptedException {

        UploadImageResponse imageResponse = new UploadImageResponse();

        UploadImageRequest imageRequest = new UploadImageRequest(imageBase64);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, false);
        String jsonRequest = objectMapper.writeValueAsString(imageRequest);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        System.out.println(request);

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response);

        // Проверяем статус ответа
        if (response.statusCode() == 200) {
            // Обрабатываем JSON-ответ
            imageResponse = objectMapper.readValue(response.body(), UploadImageResponse.class);
            System.out.println("Response Image Base64: " + imageResponse.getImageBase64());
        } else {
            System.out.println("Error: " + response.statusCode());
        }

        return imageResponse;
    }

    public static void saveBase64ImageToFile(String base64String, String fileName) {
        try {
            // Декодируем строку Base64 в байтовый массив
            byte[] imageBytes = Base64.getDecoder().decode(base64String);

            // Сохраняем изображение в файл
            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                fos.write(imageBytes);
            }

            System.out.println("Изображение сохранено как: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении изображения.");
        }
    }




}
