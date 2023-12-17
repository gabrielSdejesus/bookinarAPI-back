package com.example.bookinar.dto;

import lombok.Data;

@Data
public class PhotosProductDTO {

    private Long id;
    private String name;
    private String type;
    private String pathDownload;
    private Long idProduct;
}
