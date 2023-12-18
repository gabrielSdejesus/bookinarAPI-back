package com.example.bookinar.repository.jpa;

import com.example.bookinar.entity.PhotosProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotosProductRepository extends JpaRepository<PhotosProduct, Long> {

    List<PhotosProduct> findPhotosProductsByIdProduct(Long id);
}
