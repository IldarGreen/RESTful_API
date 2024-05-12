package com.vanesabo.backend.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ProductRequest(
        @NotBlank(message = "Name cannot be empty") String name,
        @NotBlank(message = "Category cannot be empty") String category,
        @NotNull(message = "Price cannot be null") @Positive(message = "Price must be positive") BigDecimal price,
        @NotNull(message = "Available stock cannot be null") @Positive(message = "availableStock must be positive") Integer availableStock,
        @NotNull(message = "Last update date cannot be null") @PastOrPresent(message = "Last update date must be in the past or present") String lastUpdateDate,
        @NotNull(message = "Supplier ID cannot be null") Long supplierId,
        @NotNull(message = "Image ID cannot be null") UUID imageId
) {

}
