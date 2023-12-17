package com.example.bookinar.service.others;

import com.example.bookinar.dto.DownloadPhotoDTO;
import com.example.bookinar.entity.enums.Status;
import com.example.bookinar.entity.PhotosProduct;
import com.example.bookinar.entity.Product;
import com.example.bookinar.exception.BusinessException;
import com.example.bookinar.repository.PhotosProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotosProductService {

    private final PhotosProductRepository photosProductRepository;
    @Lazy
    private final ProductService productService;

    public Boolean save(MultipartFile file, Long id) throws BusinessException {
        Product product = productService.findById(id);
        try {
            PhotosProduct photosProduct = new PhotosProduct();
            photosProduct.setName(file.getOriginalFilename());
            photosProduct.setIdProduct(product.getId());
            photosProduct.setData(file.getBytes());
            photosProduct.setType(file.getContentType());
            photosProduct.setStatus(Status.ACTIVE);
            photosProduct.setDateRegister(LocalDateTime.now());
            photosProductRepository.save(photosProduct);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Boolean.TRUE;
    }

    public DownloadPhotoDTO download(Long id) {
        HttpHeaders headers = new HttpHeaders();
        PhotosProduct photosProduct = photosProductRepository.findById(id).get();

        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(photosProduct.getName()).build());
        headers.setContentType(MediaType.parseMediaType(photosProduct.getType()));
        DownloadPhotoDTO downloadPhotoDTO = new DownloadPhotoDTO();
        downloadPhotoDTO.setData(photosProduct.getData());
        downloadPhotoDTO.setHttpHeaders(headers);

        return downloadPhotoDTO;
    }

    public List<PhotosProduct> findPhotosProductsByIdProduct(Long id) {
        return photosProductRepository.findPhotosProductsByIdProduct(id);
    }
}
