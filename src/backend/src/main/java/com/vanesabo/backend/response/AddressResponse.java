package com.vanesabo.backend.response;


public record AddressResponse(
    Long id,
    String country,
    String city,
    String street
) {}
