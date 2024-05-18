package com.vanesabo.backend.response;


import java.util.UUID;

public record AddressResponse(
        UUID id,
        String country,
        String city,
        String street) {
}
