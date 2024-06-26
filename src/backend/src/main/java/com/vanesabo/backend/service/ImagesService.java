package com.vanesabo.backend.service;

import com.vanesabo.backend.model.ImagesEntity;
import com.vanesabo.backend.model.ProductEntity;
import com.vanesabo.backend.repository.ImagesRepository;
import com.vanesabo.backend.repository.ProductRepository;
import com.vanesabo.backend.request.ImagesRequest;
import com.vanesabo.backend.response.ImagesResponse;
import com.vanesabo.backend.utils.ImagesMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static com.vanesabo.backend.utils.ImagesMapper.imagesEntityToImagesResponse;

@Service
public class ImagesService {
    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private ProductRepository productRepository;
    private final static String IMAGE_NOT_FOUND = "Image not found";

    //1
    public ImagesResponse addImagesEntity(@Valid ImagesRequest request, UUID productId) {
        byte[] decodedImage = Base64.getDecoder().decode(request.image());

        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product with id: " + productId + " not found"));

        ImagesEntity newImage = new ImagesEntity(decodedImage, new ArrayList<>() {
            {
                add(productEntity);
            }
        });
        ImagesEntity savedImage = imagesRepository.save(newImage);
        productEntity.setImage(newImage);
        productRepository.save(productEntity);

        return imagesEntityToImagesResponse(savedImage);
    }

    //2
    public ImagesResponse updateImageById(UUID id, @Valid ImagesRequest request) {
        ImagesEntity existingImage = imagesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, IMAGE_NOT_FOUND + id));

        byte[] decodedImage = Base64.getDecoder().decode(request.image());
        existingImage.setImage(decodedImage);
        ImagesEntity updatedImage = imagesRepository.save(existingImage);

        return imagesEntityToImagesResponse(updatedImage);
    }

    //3
    public void deleteImageById(UUID id) {
        if (!imagesRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, IMAGE_NOT_FOUND + id);
        }
        imagesRepository.deleteById(id);
    }

    //4
    public List<ImagesResponse> getImagesByProductId(UUID productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id: " + productId + " not found"));
        List<ImagesEntity> imagesEntity = imagesRepository.findByProductsId(productId);

        return imagesEntity
                .stream()
                .map(ImagesMapper::imagesEntityToImagesResponse)
                .toList();
    }

    //5
    public ImagesResponse getImageById(UUID id) {
        ImagesEntity imageEntity = imagesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, IMAGE_NOT_FOUND + id));

        return imagesEntityToImagesResponse(imageEntity);
    }

    public ImagesEntity getImagesEntityById(UUID id) {
        return imagesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, IMAGE_NOT_FOUND + id));
    }
}
