package com.vanesabo.backend.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record SupplierRequest(
        @NotBlank(message = "Supplier name is mandatory") String name,
        @NotNull(message = "Address ID cannot be null") UUID addressId,
        @NotBlank(message = "Phone number cannot be empty") @Pattern(regexp = "\\+\\d{11}") String phoneNumber) {
}
