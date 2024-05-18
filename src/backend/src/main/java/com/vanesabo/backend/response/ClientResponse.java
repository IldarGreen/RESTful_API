package com.vanesabo.backend.response;

import java.time.LocalDate;
import java.util.UUID;

public record ClientResponse(
        UUID id,
        String clientName,
        String clientSurname,
        LocalDate birthday,
        String gender,
        LocalDate registrationDate,
        UUID addressId) {
}
