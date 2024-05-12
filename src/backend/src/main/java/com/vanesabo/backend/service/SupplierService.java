package com.vanesabo.backend.service;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.model.SupplierEntity;
import com.vanesabo.backend.repository.SupplierRepository;
import com.vanesabo.backend.request.SupplierRequest;
import com.vanesabo.backend.response.SupplierResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private AddressService addressService;

    public List<SupplierResponse> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(supplier -> new SupplierResponse(
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getAddress().getId(),
                        supplier.getPhoneNumber()))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Supplier not found with id: " + id);
        }
        supplierRepository.deleteById(id);
    }

    public SupplierResponse getSupplierById(Long id) {
        SupplierEntity supplierEntity = supplierRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Supplier not found with id: " + id));

        return new SupplierResponse(
                supplierEntity.getId(),
                supplierEntity.getName(),
                supplierEntity.getAddress().getId(),
                supplierEntity.getPhoneNumber());
    }

    public SupplierResponse addNewSupplier(SupplierRequest request) {
        AddressEntity address = addressService.getAddressEntityById(request.addressId());
        SupplierEntity newSupplierEntity = new SupplierEntity(
                UUID.randomUUID(),
                request.name(),
                address,
                request.phoneNumber());
        SupplierEntity savedSupplier = supplierRepository.save(newSupplierEntity);

        return new SupplierResponse(
                savedSupplier.getId(),
                savedSupplier.getName(),
                savedSupplier.getAddress().getId(),
                savedSupplier.getPhoneNumber());
    }

    public SupplierEntity getSupplierEntityById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Supplier not found with id: " + id));
    }

    public SupplierResponse updateSupplierAddress(Long supplierId, Long newAddressId) {
        SupplierEntity supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Supplier not found with id: " + supplierId));
        AddressEntity newAddress = addressService.getAddressEntityById(newAddressId);

        supplier.setAddress(newAddress);
        SupplierEntity updatedSupplier = supplierRepository.save(supplier);

        return new SupplierResponse(
                updatedSupplier.getId(),
                updatedSupplier.getName(),
                updatedSupplier.getAddress().getId(),
                updatedSupplier.getPhoneNumber());
    }

}
