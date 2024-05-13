package com.vanesabo.backend.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ClientRequest(
        @NotBlank(message = "Client name is mandatory") String clientName,
        @NotBlank(message = "Client surname is mandatory") String clientSurname,
        @Past(message = "Birthday date cannot cannot be later than yesterday") LocalDate birthday,
        @Pattern(regexp = "M|F", message = "Gender must be 'M' or 'F'") String gender,
        @PastOrPresent(message = "Registration date cannot be later than today") LocalDate registrationDate,
        @NotNull(message = "Address ID cannot be null") Long addressId) {
}
