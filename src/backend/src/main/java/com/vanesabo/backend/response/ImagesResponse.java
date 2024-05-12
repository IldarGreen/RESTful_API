package com.vanesabo.backend.response;

import java.util.UUID;

public record ImagesResponse(
        UUID id,
        byte[] image
) {}
