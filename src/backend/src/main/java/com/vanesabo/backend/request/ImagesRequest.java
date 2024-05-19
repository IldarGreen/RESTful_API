package com.vanesabo.backend.request;

import jakarta.validation.constraints.NotBlank;

public record ImagesRequest(
        @NotBlank(message = "The string containing the data must not be empty") String image) {
}
