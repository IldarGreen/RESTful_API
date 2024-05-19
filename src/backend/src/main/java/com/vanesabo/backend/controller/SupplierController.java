package com.vanesabo.backend.controller;

import com.vanesabo.backend.request.AddressRequest;
import com.vanesabo.backend.request.SupplierRequest;
import com.vanesabo.backend.response.SupplierResponse;
import com.vanesabo.backend.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/suppliers")
@Tag(name = "Supplier", description = "Operations related to supplier")
public class SupplierController {

    @Autowired
    public SupplierService supplierService;

    //1
    @PostMapping
    @Operation(summary = "Add a new supplier", description = "Adding a new supplier to the database.")
    public ResponseEntity<SupplierResponse> addNewSupplier(@Valid @RequestBody SupplierRequest request) {
        SupplierResponse newSupplier = supplierService.addNewSupplier(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSupplier);
    }

    //2
    @PatchMapping("/{id}/address")
    @Operation(summary = "Update supplier address", description = "Update the address of a specific supplier")
    public ResponseEntity<SupplierResponse> updateSupplierAddress(
            @PathVariable UUID id,
            @Valid @RequestBody AddressRequest request) {
        return supplierService.updateSupplierAddress(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //3
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete supplier by ID", description = "Delete a supplier with a given identifier")
    public void deleteSupplierById(@PathVariable UUID id) {
        supplierService.deleteById(id);
    }

    //4
    @GetMapping
    @Operation(summary = "Get list of all suppliers", description = "Retrieves a list of suppliers.")
    public ResponseEntity<List<SupplierResponse>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    //5
    @GetMapping("/{id}")
    @Operation(summary = "Get supplier by ID", description = "Get a supplier by a specific id")
    public ResponseEntity<SupplierResponse> getSupplierById(@PathVariable UUID id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

}
