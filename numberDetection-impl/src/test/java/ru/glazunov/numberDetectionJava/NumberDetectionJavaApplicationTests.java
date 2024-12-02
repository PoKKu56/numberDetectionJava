package ru.glazunov.numberDetectionJava;

import org.aspectj.lang.annotation.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.testng.annotations.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.glazunov.numberDetectionJava.service.UploadImageService;

import java.io.IOException;
import java.util.Base64;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

@SpringBootTest
class NumberDetectionJavaApplicationTests {

	@Test
	void contextLoads() {

	}

	@Mock
	private Model model;

	@InjectMocks
	private UploadImageService uploadImageService;

	@Before("")
    public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	// Тестирование метода imageToByte64
	@Test
	public void testImageToByte64() throws IOException {
		byte[] imageBytes = "test image".getBytes();
		MockMultipartFile file = new MockMultipartFile("image", "test.png", "image/png", imageBytes);

		String base64Image = uploadImageService.imageToByte64(file);

		// Проверяем, что строка base64 не пустая
		assertNotNull(base64Image);

		// Проверяем, что она декодируется обратно в исходный массив байтов
		byte[] decodedBytes = Base64.getDecoder().decode(base64Image);
		assertArrayEquals(imageBytes, decodedBytes);
	}



}
