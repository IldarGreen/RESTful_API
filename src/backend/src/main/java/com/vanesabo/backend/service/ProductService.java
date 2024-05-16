package com.vanesabo.backend.service;

import com.vanesabo.backend.model.ImagesEntity;
import com.vanesabo.backend.model.ProductEntity;
import com.vanesabo.backend.model.SupplierEntity;
import com.vanesabo.backend.repository.ProductRepository;
import com.vanesabo.backend.request.ProductRequest;
import com.vanesabo.backend.response.ProductResponse;
import com.vanesabo.backend.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.vanesabo.backend.utils.ProductMapper.productEntityToProductResponse;
import static com.vanesabo.backend.utils.ProductMapper.productRequestToProductEntity;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImagesService imagesService;
    @Autowired
    private SupplierService supplierService;

    //1
    public ProductResponse addProduct(ProductRequest productRequest) {

        SupplierEntity supplierEntity = supplierService.getSupplierEntityById(productRequest.supplierId());
        ImagesEntity imagesEntity = imagesService.getImagesEntityById(productRequest.imageId());

//        ProductEntity newEntity = new ProductEntity(
//                productRequest.name(),
//                productRequest.category(),
//                productRequest.price(),
//                productRequest.availableStock(),
//                productRequest.lastUpdateDate(),
//                supplierEntity,
//                imagesEntity);
//
//        ProductEntity savedProduct = productRepository.save(newEntity);
//
//        return new ProductResponse(
//                savedProduct.getId(),
//                savedProduct.getName(),
//                savedProduct.getCategory(),
//                savedProduct.getPrice(),
//                savedProduct.getAvailableStock(),
//                savedProduct.getLastUpdateDate(),
//                savedProduct.getSupplier().getId(),
//                savedProduct.getImage().getId());

        ProductEntity newEntity = productRequestToProductEntity(productRequest, supplierEntity, imagesEntity);
        ProductEntity savedProduct = productRepository.save(newEntity);

        return productEntityToProductResponse(savedProduct);
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

//        return new ProductResponse(
//                updatedProduct.getId(),
//                updatedProduct.getName(),
//                updatedProduct.getCategory(),
//                updatedProduct.getPrice(),
//                updatedProduct.getAvailableStock(),
//                updatedProduct.getLastUpdateDate(),
//                updatedProduct.getSupplier().getId(),
//                updatedProduct.getImage().getId());
        return productEntityToProductResponse(updatedProduct);
    }

    //3
    public ProductResponse getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product with id: " + id + " not found"));
//        return new ProductResponse(
//                productEntity.getId(),
//                productEntity.getName(),
//                productEntity.getCategory(),
//                productEntity.getPrice(),
//                productEntity.getAvailableStock(),
//                productEntity.getLastUpdateDate(),
//                productEntity.getSupplier().getId(),
//                productEntity.getImage().getId());
        return productEntityToProductResponse(productEntity);

    }

    //4
    public List<ProductResponse> getAllProducts() {
//        return productRepository.findAll().stream()
//                .map(product -> new ProductResponse(
//                        product.getId(),
//                        product.getName(),
//                        product.getCategory(),
//                        product.getPrice(),
//                        product.getAvailableStock(),
//                        product.getLastUpdateDate(),
//                        product.getSupplier().getId(),
//                        product.getImage().getId()))
//                .collect(Collectors.toList());
        return productRepository.findAll().stream()
                .map(ProductMapper::productEntityToProductResponse)
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
