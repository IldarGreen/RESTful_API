package com.vanesabo.backend.controller;

import com.vanesabo.backend.request.AddressRequest;
import com.vanesabo.backend.request.SupplierRequest;
import com.vanesabo.backend.response.SupplierResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    public SupplierService supplierService;
    //1
    @PostMapping
    ResponseEntity<SupplierResponse> addNewSupplier(@Valid @RequestBody SupplierRequest request) {
        SupplierResponse newSupplier = supplierService.addNewSupplier(request);
        return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
    }

    //2
    @PatchMapping("/{id}")
//    ResponseEntity<SupplierResponse> updateSupplierAddress(@PathVariable Long id, @RequestParam Long newAddress) {
    //////////////////////////------------------------------------------------------------------------------------------------------
    ResponseEntity<SupplierResponse> updateSupplierAddress(@PathVariable Long id, @RequestBody AddressRequest newAddress) {
        return new ResponseEntity<>(supplierService.updateSupplierAddress(id, newAddress), HttpStatus.CREATED);
    }

    //3
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id) {
        supplierService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //4
    @GetMapping
    ResponseEntity<List<SupplierResponse>> getAllSuppliers() {
        List<SupplierResponse> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    //5
    @GetMapping("/{id}")
    ResponseEntity<SupplierResponse> getSupplierById(@PathVariable Long id) {
        SupplierResponse supplier = supplierService.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }
}
