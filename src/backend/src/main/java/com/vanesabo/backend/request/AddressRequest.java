package com.vanesabo.backend.request;


import jakarta.validation.constraints.NotBlank;


public record AddressRequest(
        @NotBlank(message = "Country field is mandatory") String country,
        @NotBlank(message = "City field is mandatory") String city,
        @NotBlank(message = "Street field is mandatory") String street
) {
}
