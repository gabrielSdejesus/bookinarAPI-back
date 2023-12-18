package com.example.bookinar.service.others;

import com.example.bookinar.dto.ProductDetailsDTO;
import com.example.bookinar.entity.ProductDetails;
import com.example.bookinar.repository.mongodb.ProductDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;
    private final ObjectMapper objectMapper;


    public ProductDetailsDTO save(ProductDetailsDTO productDetailsDTO) {
        ProductDetails productDetails = objectMapper.convertValue(productDetailsDTO, ProductDetails.class);
        return objectMapper.convertValue(productDetailsRepository.save(productDetails), ProductDetailsDTO.class);
    }

    public ProductDetailsDTO findByIdProduct(Long idProduct) {
        ProductDetails productDetails = productDetailsRepository.findByIdProduct(idProduct);
        ProductDetailsDTO productDetailsDTO = objectMapper.convertValue(productDetails, ProductDetailsDTO.class);
        productDetailsDTO.setIdProduct(productDetails.getIdProduct());
        return productDetailsDTO;
    }
}
