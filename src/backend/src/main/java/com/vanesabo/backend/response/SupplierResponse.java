package com.vanesabo.backend.response;


public record SupplierResponse(
        Long id,
        String name,
        Long addressId,
        String phoneNumber) {
}
