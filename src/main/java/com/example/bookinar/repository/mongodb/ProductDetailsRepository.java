package com.example.bookinar.repository.mongodb;
import com.example.bookinar.entity.ProductDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends MongoRepository<ProductDetails, String> {

    ProductDetails findByIdProduct(Long id);
}
