package com.example.bookinar.service.others;

import com.example.bookinar.dto.PageDTO;
import com.example.bookinar.dto.ProductDTO;
import com.example.bookinar.dto.ProductDetailsDTO;
import com.example.bookinar.entity.PhotosProduct;
import com.example.bookinar.entity.Product;
import com.example.bookinar.entity.enums.Status;
import com.example.bookinar.exception.BusinessException;
import com.example.bookinar.repository.jpa.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;
    private final PhotosProductService photosProductService;
    private final ProductDetailsService productDetailsService;

    public ProductService (ProductRepository productRepository, ObjectMapper objectMapper, @Lazy PhotosProductService photosProductService, ProductDetailsService productDetailsService) {
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
        this.photosProductService = photosProductService;
        this.productDetailsService = productDetailsService;
    }

    public ProductDTO save (ProductDTO productDTO) {
        Product product = objectMapper.convertValue(productDTO, Product.class);
        product.setStatus(Status.ACTIVE);
        product.setDateRegister(LocalDateTime.now());
        product.setRating(0);

        product = productRepository.save(product);
        productDTO.getProductDetails().setIdProduct(product.getId());
        ProductDetailsDTO productDetailsDTO = productDetailsService.save(productDTO.getProductDetails());

        productDTO = objectMapper.convertValue(product, ProductDTO.class);
        productDTO.setId(product.getId());
        productDTO.setStatus(product.getStatus());
        productDTO.setRating(product.getRating());
        productDTO.setProductDetails(productDetailsDTO);
        return productDTO;
    }

    public PageDTO<ProductDTO> getAll (Integer page, Integer size) {
        return getlistProductDTOPageable(productRepository.findAll(PageRequest.of(page, size)), page, size);
    }

    public Product findById (Long id) throws BusinessException {
        return productRepository.findById(id).orElseThrow(() -> new BusinessException("Product Not Found!"));
    }

    public ProductDTO findByIdDTO (Long id) throws BusinessException {
        Product product = findById(id);
        List<PhotosProduct> photosProducts = getlistPhotosProduct(product.getId());
        ProductDetailsDTO productDetailsDTO = productDetailsService.findByIdProduct(product.getId());
        ProductDTO productDTO = objectMapper.convertValue(product, ProductDTO.class);
        productDTO.setProductDetails(productDetailsDTO);
        productDTO.setStatus(product.getStatus());
        productDTO.setId(product.getId());
        productDTO.setRating(product.getRating());
        productDTO.setPhotos(objectMapper.convertValue(photosProducts, new TypeReference<>() {}));
        productDTO.getPhotos().forEach((photo) -> {
            photo.setPathDownload(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/photosproduct/download/")
                    .path(photo.getId().toString())
                    .toUriString());
        });
        return productDTO;
    }

    protected List<PhotosProduct> getlistPhotosProduct (Long id) {
        return photosProductService.findPhotosProductsByIdProduct(id);
    }

    protected PageDTO<ProductDTO> getlistProductDTOPageable (Page<Product> products, Integer page, Integer size){
        List<ProductDTO> listProducts = products
                .map(product -> {
                            List<PhotosProduct> photosProducts = getlistPhotosProduct(product.getId());
                            ProductDetailsDTO productDetailsDTO = productDetailsService.findByIdProduct(product.getId());
                            ProductDTO productDTO = objectMapper.convertValue(product, ProductDTO.class);
                            productDTO.setProductDetails(productDetailsDTO);
                            productDTO.setStatus(product.getStatus());
                            productDTO.setId(product.getId());
                            productDTO.setRating(product.getRating());
                            productDTO.setPhotos(objectMapper.convertValue(photosProducts, new TypeReference<>() {}));
                            productDTO.getPhotos().forEach((photo) -> {
                                photo.setPathDownload(ServletUriComponentsBuilder.fromCurrentContextPath()
                                        .path("/photosproduct/download/")
                                        .path(photo.getId().toString())
                                        .toUriString());
                            });
                            return productDTO;
                        }).toList();

        return new PageDTO<>(products.getTotalElements(),
                products.getTotalPages(),
                page,
                size,
                listProducts);
    }
}