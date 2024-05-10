package com.vanesabo.backend.response;

public record ClientResponse(
        Long id,
        String clientName,
        String clientSurname,
        String birthday,
        String gender,
        String registrationDate,
        Long addressId
) {}
