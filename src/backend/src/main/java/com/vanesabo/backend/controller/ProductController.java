package com.vanesabo.backend.controller;

import com.vanesabo.backend.request.ProductRequest;
import com.vanesabo.backend.response.ProductResponse;
import com.vanesabo.backend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //1
    @PostMapping
    ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest product) {
        ProductResponse newProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }
    //2
    @PatchMapping("/{id}")
    ResponseEntity<ProductResponse> reduceAvailableStock(@PathVariable Long id, @RequestParam Integer amount) {
        ProductResponse updatedProduct = productService.reduceAvailableStock(id, amount);
        return ResponseEntity.ok(updatedProduct);
    }
    //3
    @GetMapping("/{id}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
    //4
    @GetMapping
    ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    //5
    @GetMapping("/{id}/delete")
    ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
