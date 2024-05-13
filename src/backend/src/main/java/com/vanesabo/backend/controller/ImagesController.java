package com.vanesabo.backend.controller;

import com.vanesabo.backend.request.ImagesRequest;
import com.vanesabo.backend.response.ImagesResponse;
import com.vanesabo.backend.service.ImagesService;
import com.vanesabo.backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/images")
@Tag(name = "Image", description = "Operations related to image")
public class ImagesController {

    @Autowired
    private ImagesService imagesService;
    @Autowired
    private ProductService productService;

    //1
    @PostMapping(value = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add new image", description = "Adding a new image to the database")
        ResponseEntity<ImagesResponse> addImage(@Valid @RequestBody ImagesRequest request, @PathVariable Long productId) {
        ImagesResponse newImage = imagesService.addImagesEntity(request, productId);
        return new ResponseEntity<>(newImage, HttpStatus.CREATED);
    }

    //2
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update image by ID", description = "Replaces the image with the one that arrived")
        ResponseEntity<ImagesResponse> updateImageById(@PathVariable UUID id, @Valid  @RequestBody ImagesRequest request) {
        return new ResponseEntity<>(imagesService.updateImageById(id, request), HttpStatus.OK);
    }

    //3
    @GetMapping("/{id}/delete")
    @Operation(summary = "Delete image by ID", description = "Delete an image with a given identifier")
    ResponseEntity<Void> deleteImageById(@PathVariable UUID id) {
        imagesService.deleteImageById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity.ok().build();
    }

    //4
    @GetMapping("/product/{productId}")
    @Operation(summary = "Get images by product ID", description = "Returns an image for the selected product")
    ResponseEntity<List<ImagesResponse>> getImagesByProductId(@PathVariable Long productId) {
        return new ResponseEntity<>(imagesService.getImagesByProductId(productId), HttpStatus.OK);
    }

    //5
    @GetMapping("/{id}")
    @Operation(summary = "Get image by ID", description = "Get an imag by a specific id")
    ResponseEntity<ImagesResponse> getImageById(@PathVariable UUID id) {
        return new ResponseEntity<>(imagesService.getImageById(id), HttpStatus.OK);
    }

}
