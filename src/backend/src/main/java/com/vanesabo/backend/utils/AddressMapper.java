package com.vanesabo.backend.utils;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.request.AddressRequest;
import com.vanesabo.backend.response.AddressResponse;

public class AddressMapper {
    public static AddressEntity addressRequestToAddressEntity(AddressRequest addressRequest) {
        return new AddressEntity(
                addressRequest.country(),
                addressRequest.city(),
                addressRequest.street()
        );
    }

    public static AddressResponse addressEntityToAddressResponse(AddressEntity addressEntity) {
        return new AddressResponse(
                addressEntity.getId(),
                addressEntity.getCountry(),
                addressEntity.getCity(),
                addressEntity.getStreet()
        );
    }
}
