package com.vanesabo.backend.controller;

import com.vanesabo.backend.request.AddressRequest;
import com.vanesabo.backend.response.AddressResponse;
import com.vanesabo.backend.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@Tag(name = "Address", description = "Operations related to address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    @Operation(summary = "Add a new address", description = "Adding a new address to the database.")
    public ResponseEntity<AddressResponse> addAddress(@Valid @RequestBody AddressRequest request) {
        return ResponseEntity.ok().body(addressService.addAddress(request));
    }

    @Operation(summary = "Get all addresses", description = "Getting a list of all addresses from the database.")
    @GetMapping()
    public ResponseEntity<List<AddressResponse>> getAllAddresses() {
        return ResponseEntity.ok().body(addressService.getAllAddresses());
    }
}
