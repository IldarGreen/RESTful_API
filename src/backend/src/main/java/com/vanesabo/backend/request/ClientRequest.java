package com.vanesabo.backend.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public record ClientRequest(
        @NotBlank(message = "Client name must be filled in") String clientName,
//        @NotNull(message = "Client name must be filled in") String clientName,
//        @NotBlank(message = "Client surname must be filled in") String clientSurname,
        @NotNull(message = "Client surname must be filled in") String clientSurname,
        @Past(message = "Birthday must be in the past") String birthday,
//        String birthday,
        @Pattern(regexp = "M|F", message = "Gender must be 'M' or 'F'") String gender,
        @NotBlank(message = "Registration date must be filled in") String registrationDate,
//        @NotNull(message = "Address ID cannot be null") Long addressId
        Long addressId
) {}
