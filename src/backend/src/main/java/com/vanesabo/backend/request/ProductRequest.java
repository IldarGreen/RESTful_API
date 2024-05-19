package com.vanesabo.backend.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ProductRequest(
        @NotBlank(message = "Product name is mandatory") String name,
        @NotBlank(message = "Category selection is mandatory") String category,
        @Positive(message = "Price must be positive") BigDecimal price,
        @Positive(message = "AvailableStock must be positive") Integer availableStock,
        @PastOrPresent(message = "Last update date cannot be later than today") LocalDate lastUpdateDate,
        @NotNull(message = "Supplier ID cannot be null") UUID supplierId,
        @NotNull(message = "Image ID cannot be null") UUID imageId) {
}
