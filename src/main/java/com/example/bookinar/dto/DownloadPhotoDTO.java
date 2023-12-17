package com.example.bookinar.dto;

import lombok.Data;
import org.springframework.http.HttpHeaders;

@Data
public class DownloadPhotoDTO {

    private HttpHeaders httpHeaders;
    private byte[] data;
}
