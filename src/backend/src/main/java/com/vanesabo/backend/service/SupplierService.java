package com.vanesabo.backend.service;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.model.SupplierEntity;
import com.vanesabo.backend.repository.SupplierRepository;
import com.vanesabo.backend.request.AddressRequest;
import com.vanesabo.backend.request.SupplierRequest;
import com.vanesabo.backend.response.ClientResponse;
import com.vanesabo.backend.response.SupplierResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private AddressService addressService;

    //1
    public SupplierResponse addNewSupplier(SupplierRequest request) {
        AddressEntity address = addressService.getAddressEntityById(request.addressId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Address not found with id: " + request.addressId()));
        SupplierEntity newSupplierEntity = new SupplierEntity(
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

    //2
    public Optional<SupplierResponse> updateSupplierAddress(Long supplierId, AddressRequest request) {
        Optional<AddressEntity> addressEntity = addressService.getAddressByAllField(request.country(), request.city(), request.street());

        if (addressEntity.isEmpty()) {
            Long addressId = addressService.addAddress(request).id();
            addressEntity = addressService.getAddressEntityById(addressId);
        }

        Optional<AddressEntity> finalAddressEntity = addressEntity;
        return supplierRepository.findById(supplierId).map(supplier -> {
            if (finalAddressEntity.isPresent()) {
                supplier.setAddress(finalAddressEntity.get());
            }

            supplierRepository.save(supplier);
            return new SupplierResponse(
                supplier.getId(),
                supplier.getName(),
                supplier.getAddress().getId(),
                supplier.getPhoneNumber());
        });
    }

    //3
    public void deleteById(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Supplier not found with id: " + id);
        }
        supplierRepository.deleteById(id);
    }

    //4
    public List<SupplierResponse> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(supplier -> new SupplierResponse(
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getAddress().getId(),
                        supplier.getPhoneNumber()))
                .collect(Collectors.toList());
    }

    //5
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

    public SupplierEntity getSupplierEntityById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Supplier not found with id: " + id));
    }

}
