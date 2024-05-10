package com.vanesabo.backend.service;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.repository.AddressRepository;
import com.vanesabo.backend.response.AddressResponse;
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


    public List<AddressEntity> findAll() {
        return addressRepository.findAll();
    }

    public Optional<AddressEntity> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public AddressEntity getAddressEntityById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Address not found with id: " + id));
    }

    public List<AddressResponse> getAllAddreses() {
        List<AddressEntity> addressEntities = addressRepository.findAll();

        return addressEntities.stream().map(address -> new AddressResponse(
                        address.getId(),
                        address.getCountry(),
                        address.getCity(),
                        address.getStreet()))
                .toList();
    }

}
