package com.vanesabo.backend.controller;

import com.vanesabo.backend.request.ProductRequest;
import com.vanesabo.backend.response.ProductResponse;
import com.vanesabo.backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "Operations related to products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //1
    @PostMapping
    @Operation(summary = "Add a new product", description = "Adding a new product to the database.")
    ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest product) {
        ProductResponse newProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    //2
    @PatchMapping("/{id}")
    @Operation(summary = "Reduce available stock", description = "Reduce the amount of available inventory for a given product..")
    ResponseEntity<ProductResponse> reduceAvailableStock(@PathVariable Long id, @RequestParam Integer amount) {
        ProductResponse updatedProduct = productService.reduceAvailableStock(id, amount);
        return ResponseEntity.ok(updatedProduct);
    }

    //3
    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Get a product by a specific id")
    ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    //4
    @GetMapping
    @Operation(summary = "Get list of all clients", description = "Retrieves a list of clients.")
    ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    //5
    @GetMapping("/{id}/delete")
    @Operation(summary = "Delete product by ID", description = "Delete a product with a given identifier")
    ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
