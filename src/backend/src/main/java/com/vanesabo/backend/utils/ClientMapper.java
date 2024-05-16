package com.vanesabo.backend.utils;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.model.ClientEntity;
import com.vanesabo.backend.request.ClientRequest;
import com.vanesabo.backend.response.ClientResponse;

public class ClientMapper {
    public static ClientEntity clientRequestToClientEntity(ClientRequest clientRequest, AddressEntity addressEntity) {
        return new ClientEntity(
                clientRequest.clientName(),
                clientRequest.clientSurname(),
                clientRequest.birthday(),
                clientRequest.gender(),
                clientRequest.registrationDate(),
                addressEntity
        );
    }

    public static ClientResponse clientEntityToClientResponse(ClientEntity clientEntity) {
        return new ClientResponse(
                clientEntity.getId(),
                clientEntity.getClientName(),
                clientEntity.getClientSurname(),
                clientEntity.getBirthday(),
                clientEntity.getGender(),
                clientEntity.getRegistrationDate(),
                clientEntity.getAddress().getId()
        );
    }
}
