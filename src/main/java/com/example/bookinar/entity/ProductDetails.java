package com.example.bookinar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "PRODUCTDETAILS")
@Document(collection = "PRODUCTDETAILS")
public class ProductDetails {


    @Id
    private String id;

    @Indexed(unique = true)
    private Long idProduct;
    private String specifications;
    private String detailedDescription;
    private String usageTips;
}