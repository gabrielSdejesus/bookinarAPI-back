package com.example.bookinar.entity.enums;

import java.util.Arrays;

public enum TypeProduct {
    BOOK("BK"),
    AUDIOBOOK("AB"),
    EBOOK("EB");

    private final String type;

    TypeProduct(String type) {
        this.type = type;
    }

    public String getTypeProduct() {
        return type;
    }

    public static TypeProduct getTypeProduct(String type) {
        return Arrays.stream(TypeProduct.values())
                .filter(sts -> sts.getTypeProduct().equals(type))
                .findFirst()
                .get();
    }
}
