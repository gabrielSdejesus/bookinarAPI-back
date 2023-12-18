package com.example.bookinar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDetailsDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long idProduct;
    private String specifications;
    private String detailedDescription;
    private String usageTips;
}