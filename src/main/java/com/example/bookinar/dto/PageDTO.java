package com.example.bookinar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageDTO<T>{

    private Long totalElements;
    private Integer amountPages;
    private Integer page;
    private Integer size;
    private List<T> elements;
}
