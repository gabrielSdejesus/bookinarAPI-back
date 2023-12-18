package com.example.bookinar.dto;


import com.example.bookinar.entity.enums.Status;
import com.example.bookinar.entity.enums.TypeProduct;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private TypeProduct typeProduct;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Status status;
    private String name;
    private BigDecimal price;
    private String description;
    private Integer amountStock;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer rating;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<PhotosProductDTO> photos;
    private ProductDetailsDTO productDetails;
}
