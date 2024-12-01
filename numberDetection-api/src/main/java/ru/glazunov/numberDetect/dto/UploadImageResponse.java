package ru.glazunov.numberDetect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UploadImageResponse {

    private Integer responseCode;
    private String responseMessage;
    private String imageResponseBase64;
    private String fileTypeResponseBase64;
    private String fileNameResponseBase64;

}
