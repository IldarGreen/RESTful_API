package com.vanesabo.backend.service;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.model.SupplierEntity;
import com.vanesabo.backend.repository.SupplierRepository;
import com.vanesabo.backend.request.AddressRequest;
import com.vanesabo.backend.request.SupplierRequest;
import com.vanesabo.backend.response.SupplierResponse;
import com.vanesabo.backend.utils.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.vanesabo.backend.utils.SupplierMapper.productEntityToSupplierResponse;
import static com.vanesabo.backend.utils.SupplierMapper.productRequestToSupplierEntity;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private AddressService addressService;

    //1
    public SupplierResponse addNewSupplier(SupplierRequest supplierRequest) {
        AddressEntity addressEntity = addressService.getAddressEntityById(supplierRequest.addressId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Address with id: " + supplierRequest.addressId() + " not found"));
        SupplierEntity newSupplierEntity = productRequestToSupplierEntity(supplierRequest, addressEntity);
        SupplierEntity savedSupplier = supplierRepository.save(newSupplierEntity);

        return productEntityToSupplierResponse(savedSupplier);
    }

    //2
    public Optional<SupplierResponse> updateSupplierAddress(UUID supplierId, AddressRequest request) {
        Optional<AddressEntity> finalAddressEntity = addressService.addressAddPrepare(request, addressService);
        return supplierRepository.findById(supplierId).map(supplierEntity -> {
            finalAddressEntity.ifPresent(supplierEntity::setAddress);
            supplierRepository.save(supplierEntity);
            return productEntityToSupplierResponse(supplierEntity);
        });
    }

    //3
    public void deleteById(UUID id) {
        if (!supplierRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Supplier with id: " + id + " not found");
        }
        supplierRepository.deleteById(id);
    }

    //4
    public List<SupplierResponse> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(SupplierMapper::productEntityToSupplierResponse)
                .toList();
    }

    //5
    public SupplierResponse getSupplierById(UUID id) {
        SupplierEntity supplierEntity = supplierRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Supplier with id: " + id + " not found"));

        return productEntityToSupplierResponse(supplierEntity);
    }

    public SupplierEntity getSupplierEntityById(UUID id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Supplier with id: " + id + " not found"));
    }

}
