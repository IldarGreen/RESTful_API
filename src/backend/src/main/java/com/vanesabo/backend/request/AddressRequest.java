package com.vanesabo.backend.request;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
//    @NotBlank(message = "Country cannot be empty") String country,
//    @NotBlank(message = "City cannot be empty") String city,
//    @NotBlank(message = "Street cannot be empty") String street
//        Long id,
        String country,
        String city,
        String street
) {}
