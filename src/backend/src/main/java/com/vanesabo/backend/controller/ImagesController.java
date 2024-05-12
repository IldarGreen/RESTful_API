package com.vanesabo.backend.controller;

import com.vanesabo.backend.request.ImagesRequest;
import com.vanesabo.backend.response.ImagesResponse;
import com.vanesabo.backend.service.ImagesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/images")
public class ImagesController {

    @Autowired
    private ImagesService imagesService;

    //1
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    ResponseEntity<ImagesResponse> addImage(@Valid @RequestBody ImagesRequest request) {
    ResponseEntity<ImagesResponse> addImage(@Valid @RequestBody ImagesRequest request, @PathVariable Long productId) {
        ImagesResponse newImage = imagesService.addImagesEntity(request, productId);
        return new ResponseEntity<>(newImage, HttpStatus.CREATED);
    }

    //2
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ImagesResponse> updateImageById(@PathVariable UUID id, @Valid @RequestBody ImagesRequest request) {
        return new ResponseEntity<>(imagesService.updateImageById(id, request), HttpStatus.OK);
    }

    //3
    @GetMapping("/{id}/delete")
    ResponseEntity<Void> deleteImageById(@PathVariable UUID id) {
        imagesService.deleteImageById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity.ok().build();
    }

    //4
    @GetMapping("/byproduct/{productId}")
    ResponseEntity<List<ImagesResponse>> getImagesByProductId(@PathVariable Long productId) {
        return new ResponseEntity<>(imagesService.getImagesByProductId(productId), HttpStatus.OK);
    }

    //5
    @GetMapping("/{id}")
    ResponseEntity<ImagesResponse> getImageById(@PathVariable UUID id) {
        return new ResponseEntity<>(imagesService.getImageById(id), HttpStatus.OK);
    }
}
