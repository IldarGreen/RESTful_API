package com.vanesabo.backend.service;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.repository.AddressRepository;
import com.vanesabo.backend.request.AddressRequest;
import com.vanesabo.backend.response.AddressResponse;
import com.vanesabo.backend.utils.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.vanesabo.backend.utils.AddressMapper.addressEntityToAddressResponse;
import static com.vanesabo.backend.utils.AddressMapper.addressRequestToAddressEntity;

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

    public Optional<AddressEntity> getAddressByAllField(String country, String city, String street) {
        return addressRepository.getAddressByAllField(country, city, street);
    }

    public Optional<AddressEntity> getAddressEntityById(Long id) {
        return addressRepository.findById(id);
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                "Address not found with id: " + id));
    }

    public List<AddressResponse> getAllAddreses() {
        List<AddressEntity> addressEntities = addressRepository.findAll();

//        return addressEntities.stream().map(address -> new AddressResponse(
//                        address.getId(),
//                        address.getCountry(),
//                        address.getCity(),
//                        address.getStreet()))
//                .toList();
        return addressEntities
                .stream()
                .map(AddressMapper::addressEntityToAddressResponse)
                .toList();
    }

    public AddressResponse addAddress(AddressRequest addressRequest) {
//        AddressEntity addressEntity = new AddressEntity(
//                addressRequest.country(),
//                addressRequest.city(),
//                addressRequest.street()
//                );
//        addressRepository.save(addressEntity);
//
//        return new AddressResponse(
//                addressEntity.getId(),
//                addressEntity.getCountry(),
//                addressEntity.getCity(),
//                addressEntity.getStreet());

        AddressEntity addressEntity = addressRequestToAddressEntity(addressRequest);
        addressRepository.save(addressEntity);

        return addressEntityToAddressResponse(addressEntity);
    }

}
