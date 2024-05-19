package com.vanesabo.backend.response;


import java.util.UUID;

public record SupplierResponse(
        UUID id,
        String name,
        UUID addressId,
        String phoneNumber) {
}
