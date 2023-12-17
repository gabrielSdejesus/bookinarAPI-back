package com.example.bookinar.controller;

import com.example.bookinar.dto.DownloadPhotoDTO;
import com.example.bookinar.exception.BusinessException;
import com.example.bookinar.service.others.PhotosProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photosproduct")
public class PhotosProductController {

    private final PhotosProductService photosProductService;

    @PostMapping(value = "/save/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Boolean> save(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) throws BusinessException {
        return new ResponseEntity<>(photosProductService.save(file, id), HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id) {
        DownloadPhotoDTO downloadPhotoDTO = photosProductService.download(id);
        return new ResponseEntity<>(downloadPhotoDTO.getData(), downloadPhotoDTO.getHttpHeaders(), HttpStatus.OK);
    }
}
