package ru.glazunov.numberDetect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadImageRequest {

    private String imageBase64;
    private String fileName;
    private String filetype;

}
