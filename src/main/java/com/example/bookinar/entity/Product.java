package com.example.bookinar.entity;

import com.example.bookinar.entity.enums.Status;
import com.example.bookinar.entity.enums.TypeProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "PRODUCT")
@Entity(name = "PRODUCT")
public class Product {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT_STOCK")
    private Integer amountStock;

    @Column(name = "RATING")
    private Integer rating;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private TypeProduct typeProduct;

    @JsonIgnore
    @OneToMany(mappedBy = "products", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<PhotosProduct> photos;

    @Column(name = "DATE_REGISTER")
    private LocalDateTime dateRegister;

    @Column(name = "DATE_MODIFY")
    private LocalDateTime dateModify;

    @Column(name = "STATUS")
    @Enumerated(EnumType.ORDINAL)
    private Status status;
}