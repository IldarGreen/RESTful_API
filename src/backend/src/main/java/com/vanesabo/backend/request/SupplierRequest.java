package com.vanesabo.backend.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SupplierRequest(
        @NotBlank(message = "Name cannot be empty") String name,
        @NotNull(message = "Address ID cannot be null") UUID addressId,
        @NotBlank(message = "Phone number cannot be empty") String phoneNumber) {
}
