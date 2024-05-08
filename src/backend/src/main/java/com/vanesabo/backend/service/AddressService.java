package com.vanesabo.backend.service;

import com.vanesabo.backend.entities.AddressEntity;
import com.vanesabo.backend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Optional<AddressEntity> getAddressById(UUID id) {
        return addressRepository.findById(id);
    }

//    public AddressEntity getAddressEntityById(UUID id) {
//        return addressRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                "Address not found with id: " + id));
//    }

//    public AddressResponse addAddress(AddressRequest address) {
//        AddressEntity addressEntity = new AddressEntity(
//                UUID.randomUUID(),
//                address.country(),
//                address.city(),
//                address.street(),
//                null, null);
//        addressRepository.save(addressEntity);
//
//        return new AddressResponse(
//                addressEntity.getId(),
//                addressEntity.getCountry(),
//                addressEntity.getCity(),
//                addressEntity.getStreet());
//    }
//
//    public List<AddressResponse> getAllAddreses() {
//        List<AddressEntity> addressEntities = addressRepository.findAll();
//
//        return addressEntities.stream().map(address -> new AddressResponse(
//                        address.getId(),
//                        address.getCountry(),
//                        address.getCity(),
//                        address.getStreet()))
//                .toList();
//    }
}
