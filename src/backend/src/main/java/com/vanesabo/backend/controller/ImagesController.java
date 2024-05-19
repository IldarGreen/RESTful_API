package com.vanesabo.backend.controller;

import com.vanesabo.backend.request.ImagesRequest;
import com.vanesabo.backend.response.ImagesResponse;
import com.vanesabo.backend.service.ImagesService;
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
@RequestMapping("/images")
@Tag(name = "Image", description = "Operations related to image")
public class ImagesController {

    @Autowired
    private ImagesService imagesService;

    //1
    @PostMapping(value = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add new image", description = "Adding a new image to the database")
    public ResponseEntity<ImagesResponse> addImage(@Valid @RequestBody ImagesRequest request, @PathVariable UUID productId) {
        ImagesResponse newImage = imagesService.addImagesEntity(request, productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newImage);
    }

    //2
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update image by ID", description = "Replaces the image with the one that arrived")
    public ResponseEntity<ImagesResponse> updateImageById(@PathVariable UUID id, @Valid  @RequestBody ImagesRequest request) {
        return ResponseEntity.ok(imagesService.updateImageById(id, request));
    }

    //3
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete image by ID", description = "Delete an image with a given identifier")
    public void deleteImageById(@PathVariable UUID id) {
        imagesService.deleteImageById(id);
    }

    //4
    @GetMapping("/product/{productId}")
    @Operation(summary = "Get images by product ID", description = "Returns an image for the selected product")
    public ResponseEntity<List<ImagesResponse>> getImagesByProductId(@PathVariable UUID productId) {
        return ResponseEntity.ok().body(imagesService.getImagesByProductId(productId));
    }

    //5
    @GetMapping("/{id}")
    @Operation(summary = "Get image by ID", description = "Get an imag by a specific id")
    public ResponseEntity<ImagesResponse> getImageById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(imagesService.getImageById(id));
    }

}
