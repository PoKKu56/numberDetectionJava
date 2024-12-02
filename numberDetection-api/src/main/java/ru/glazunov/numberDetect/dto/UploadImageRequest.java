package ru.glazunov.numberDetect.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UploadImageRequest {
    @JsonProperty("image_base64")
    private String imageBase64;
}
