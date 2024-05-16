package com.vanesabo.backend.utils;

import com.vanesabo.backend.model.ImagesEntity;
import com.vanesabo.backend.model.ProductEntity;
import com.vanesabo.backend.model.SupplierEntity;
import com.vanesabo.backend.request.ProductRequest;
import com.vanesabo.backend.response.ProductResponse;

public class ProductMapper {
    public static ProductEntity productRequestToProductEntity(ProductRequest productRequest,
                                                                SupplierEntity supplierEntity,
                                                                ImagesEntity imagesEntity) {
        return new ProductEntity(
                productRequest.name(),
                productRequest.category(),
                productRequest.price(),
                productRequest.availableStock(),
                productRequest.lastUpdateDate(),
                supplierEntity,
                imagesEntity
        );
    }

    public static ProductResponse productEntityToProductResponse(ProductEntity productEntity) {
        return new ProductResponse(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getCategory(),
                productEntity.getPrice(),
                productEntity.getAvailableStock(),
                productEntity.getLastUpdateDate(),
                productEntity.getSupplier().getId(),
                productEntity.getImage().getId()
        );
    }
}
