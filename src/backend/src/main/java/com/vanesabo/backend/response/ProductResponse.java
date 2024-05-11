package com.vanesabo.backend.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ProductResponse(
        Long id,
        String name,
        String category,
        BigDecimal price,
        Integer availableStock,
        LocalDate lastUpdateDate,
        Long supplierId,
        UUID imageId
) {}
