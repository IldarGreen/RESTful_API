package com.vanesabo.backend.service;

import com.vanesabo.backend.model.ImagesEntity;
import com.vanesabo.backend.model.ProductEntity;
import com.vanesabo.backend.model.SupplierEntity;
import com.vanesabo.backend.repository.ProductRepository;
import com.vanesabo.backend.request.ProductRequest;
import com.vanesabo.backend.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImagesService imagesService;
    @Autowired
    private SupplierService supplierService;

    //1
    public ProductResponse addProduct(ProductRequest request) {

        SupplierEntity supplierEntity = supplierService.getSupplierEntityById(request.supplierId());
        ImagesEntity imagesEntity = imagesService.getImagesEntityById(request.imageId());

        ProductEntity newEntity = new ProductEntity(
                request.name(),
                request.category(),
                request.price(),
                request.availableStock(),
                request.lastUpdateDate(),
                supplierEntity,
                imagesEntity);

        ProductEntity savedProduct = productRepository.save(newEntity);

        return new ProductResponse(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getCategory(),
                savedProduct.getPrice(),
                savedProduct.getAvailableStock(),
                savedProduct.getLastUpdateDate(),
                savedProduct.getSupplier().getId(),
                savedProduct.getImage().getId());
    }

    //2
    public ProductResponse reduceAvailableStock(Long id, Integer amount) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product with id: " + id + " not found"));
        int newStock = product.getAvailableStock() - amount;

        if (newStock < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Insufficient stock for product id: " + id);
        }

        product.setAvailableStock(newStock);
        ProductEntity updatedProduct = productRepository.save(product);

        return new ProductResponse(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getCategory(),
                updatedProduct.getPrice(),
                updatedProduct.getAvailableStock(),
                updatedProduct.getLastUpdateDate(),
                updatedProduct.getSupplier().getId(),
                updatedProduct.getImage().getId());
    }

    //3
    public ProductResponse getProductById(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product with id: " + id + " not found"));
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getAvailableStock(),
                product.getLastUpdateDate(),
                product.getSupplier().getId(),
                product.getImage().getId());
    }

    //4
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getCategory(),
                        product.getPrice(),
                        product.getAvailableStock(),
                        product.getLastUpdateDate(),
                        product.getSupplier().getId(),
                        product.getImage().getId()))
                .collect(Collectors.toList());
    }

    //5
    public void deleteProduct(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product not found with id: " + id));
        productRepository.deleteById(product.getId());
    }

}
