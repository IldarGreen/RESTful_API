package com.vanesabo.backend.utils;

import com.vanesabo.backend.model.ImagesEntity;
import com.vanesabo.backend.response.ImagesResponse;

public class ImagesMapper {
    public static ImagesResponse imagesEntityToImagesResponse(ImagesEntity imagesEntity) {
        return new ImagesResponse(
                imagesEntity.getId(),
                imagesEntity.getImage()
        );
    }
}
