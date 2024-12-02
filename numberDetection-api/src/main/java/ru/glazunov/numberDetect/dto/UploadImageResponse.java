package ru.glazunov.numberDetect.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class UploadImageResponse {
    @JsonProperty("image_base64")
    private String imageBase64;
}
