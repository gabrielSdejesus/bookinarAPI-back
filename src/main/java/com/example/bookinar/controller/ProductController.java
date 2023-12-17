package com.example.bookinar.controller;

import com.example.bookinar.dto.PageDTO;
import com.example.bookinar.dto.ProductDTO;
import com.example.bookinar.exception.BusinessException;
import com.example.bookinar.service.others.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;


    @PostMapping(value = "/save", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ProductDTO> savePhotos(@RequestBody ProductDTO dto) {
        return new ResponseEntity<>(productService.save(dto), HttpStatus.OK);
    }

    @GetMapping(value = "/all/{page}/{size}")
    public ResponseEntity<PageDTO<ProductDTO>> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return new ResponseEntity<>(productService.getAll(page, size), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id) throws BusinessException {
        return new ResponseEntity<>(productService.findByIdDto(id), HttpStatus.OK);
    }
}