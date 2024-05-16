package com.vanesabo.backend.service;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.model.SupplierEntity;
import com.vanesabo.backend.repository.SupplierRepository;
import com.vanesabo.backend.request.AddressRequest;
import com.vanesabo.backend.request.SupplierRequest;
import com.vanesabo.backend.response.ClientResponse;
import com.vanesabo.backend.response.SupplierResponse;
import com.vanesabo.backend.utils.ClientMapper;
import com.vanesabo.backend.utils.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
                "Address not found with id: " + supplierRequest.addressId()));
//        SupplierEntity newSupplierEntity = new SupplierEntity(
//                request.name(),
//                addressEntity,
//                request.phoneNumber());
//        SupplierEntity savedSupplier = supplierRepository.save(newSupplierEntity);
//
//        return new SupplierResponse(
//                savedSupplier.getId(),
//                savedSupplier.getName(),
//                savedSupplier.getAddress().getId(),
//                savedSupplier.getPhoneNumber());
        SupplierEntity newSupplierEntity = productRequestToSupplierEntity(supplierRequest, addressEntity);
        SupplierEntity savedSupplier = supplierRepository.save(newSupplierEntity);

        return productEntityToSupplierResponse(savedSupplier);

    }

    //2
    public Optional<SupplierResponse> updateSupplierAddress(Long supplierId, AddressRequest request) {
        Optional<AddressEntity> addressEntity = addressService.getAddressByAllField(request.country(), request.city(), request.street());

        if (addressEntity.isEmpty()) {
            Long addressId = addressService.addAddress(request).id();
            addressEntity = addressService.getAddressEntityById(addressId);
        }

        Optional<AddressEntity> finalAddressEntity = addressEntity;
        return supplierRepository.findById(supplierId).map(supplierEntity -> {
            if (finalAddressEntity.isPresent()) {
                supplierEntity.setAddress(finalAddressEntity.get());
            }

            supplierRepository.save(supplierEntity);
//            return new SupplierResponse(
//                supplier.getId(),
//                supplier.getName(),
//                supplier.getAddress().getId(),
//                supplier.getPhoneNumber());
            return productEntityToSupplierResponse(supplierEntity);
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
//        return supplierRepository.findAll().stream()
//                .map(supplier -> new SupplierResponse(
//                        supplier.getId(),
//                        supplier.getName(),
//                        supplier.getAddress().getId(),
//                        supplier.getPhoneNumber()))
//                .collect(Collectors.toList());
        return supplierRepository.findAll().stream()
                .map(SupplierMapper::productEntityToSupplierResponse)
//                .collect(Collectors.toList());/////////////////////////////////////////////////////TODO
                .toList();/////////////////////////////////////////////??????????????????????////////TODO
    }

    //5
    public SupplierResponse getSupplierById(Long id) {
        SupplierEntity supplierEntity = supplierRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Supplier not found with id: " + id));

//        return new SupplierResponse(
//                supplierEntity.getId(),
//                supplierEntity.getName(),
//                supplierEntity.getAddress().getId(),
//                supplierEntity.getPhoneNumber());
        return productEntityToSupplierResponse(supplierEntity);
    }

    public SupplierEntity getSupplierEntityById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Supplier not found with id: " + id));
    }

}
