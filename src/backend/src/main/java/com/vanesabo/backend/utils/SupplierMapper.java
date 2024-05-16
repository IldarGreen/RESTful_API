package com.vanesabo.backend.utils;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.model.SupplierEntity;
import com.vanesabo.backend.request.SupplierRequest;
import com.vanesabo.backend.response.SupplierResponse;

public class SupplierMapper {
    public static SupplierEntity productRequestToSupplierEntity(SupplierRequest supplierRequest,
                                                                AddressEntity addressEntity) {
        return new SupplierEntity(
                supplierRequest.name(),
                addressEntity,
                supplierRequest.phoneNumber()
        );
    }

    public static SupplierResponse productEntityToSupplierResponse(SupplierEntity supplierEntity) {
        return new SupplierResponse(
                supplierEntity.getId(),
                supplierEntity.getName(),
                supplierEntity.getAddress().getId(),
                supplierEntity.getPhoneNumber()
        );
    }
}
