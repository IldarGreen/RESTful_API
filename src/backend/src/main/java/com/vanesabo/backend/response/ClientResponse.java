package com.vanesabo.backend.response;

import java.time.LocalDate;

public record ClientResponse(
        Long id,
        String clientName,
        String clientSurname,
        LocalDate birthday,
        String gender,
        LocalDate registrationDate,
        Long addressId
) {}
