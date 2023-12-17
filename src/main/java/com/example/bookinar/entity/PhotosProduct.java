package com.example.bookinar.entity;

import com.example.bookinar.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "PHOTOS_PRODUCT")
@Table(name = "PHOTOS_PRODUCT")
public class PhotosProduct  {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_PRODUCT")
    private Long idProduct;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Lob
    @Column(name = "DATA")
    @Basic(fetch = FetchType.LAZY)
    private byte[] data;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PRODUCT", referencedColumnName = "ID", insertable = false, updatable = false)
    private Product products;

    @Column(name = "DATE_REGISTER")
    private LocalDateTime dateRegister;

    @Column(name = "DATE_MODIFY")
    private LocalDateTime dateModify;

    @Column(name = "STATUS")
    @Enumerated(EnumType.ORDINAL)
    private Status status;
}