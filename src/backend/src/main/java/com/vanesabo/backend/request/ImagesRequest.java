package com.vanesabo.backend.request;

import jakarta.validation.constraints.NotBlank;

public record ImagesRequest(
        @NotBlank String image
) {
}
