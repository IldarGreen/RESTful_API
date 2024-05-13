package com.vanesabo.backend.controller;

import com.vanesabo.backend.request.AddressRequest;
import com.vanesabo.backend.request.SupplierRequest;
import com.vanesabo.backend.response.SupplierResponse;
import com.vanesabo.backend.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    @Autowired
    public SupplierService supplierService;

    //1
    @PostMapping
    public ResponseEntity<SupplierResponse> addNewSupplier(@Valid @RequestBody SupplierRequest request) {
        SupplierResponse newSupplier = supplierService.addNewSupplier(request);
        return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
    }

    //2
    @PatchMapping("/{id}/address")
    public ResponseEntity<SupplierResponse> updateSupplierAddress(
            @PathVariable Long id,
            @RequestBody AddressRequest request) {
        return supplierService.updateSupplierAddress(id, request)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //3
//    @DeleteMapping("/{id}")
    @GetMapping("/{id}/delete")
    public ResponseEntity<Void> deleteSupplierById(@PathVariable Long id) {
        supplierService.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity.ok().build();
    }

    //4
    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAllSuppliers() {
        List<SupplierResponse> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    //5
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getSupplierById(@PathVariable Long id) {
        SupplierResponse supplier = supplierService.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }
}
