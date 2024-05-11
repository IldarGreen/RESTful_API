package com.vanesabo.backend.service;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.model.ClientEntity;
import com.vanesabo.backend.repository.ClientRepository;
import com.vanesabo.backend.request.AddressRequest;
import com.vanesabo.backend.request.ClientRequest;
import com.vanesabo.backend.response.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressService addressService;

    public ClientEntity save(ClientEntity clientEntity) {
        return clientRepository.save(clientEntity);
    }

    public void deleteById(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Client with id: " + id + "not found"));
        clientRepository.deleteById(clientEntity.getId());
    }

    public List<ClientResponse> getClientsByNameAndSurname(String name, String surname) {
        List<ClientEntity> clientEntity = clientRepository.findByClientNameAndClientSurname(name, surname);
        return clientEntity.stream()
                .map(client -> new ClientResponse(
                        client.getId(),
                        client.getClientName(),
                        client.getClientSurname(),
                        client.getBirthday(),
                        client.getGender(),
                        client.getRegistrationDate(),
                        client.getAddress().getId()))
                .toList();
    }

    public ClientResponse addClient(ClientRequest request) {
        AddressEntity addressEntity = addressService.getAddressById(request.addressId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Address not found with id: " + request.addressId()));

        ClientEntity client = new ClientEntity(
                request.clientName(),
                request.clientSurname(),
                request.birthday(),
                request.gender(),
                request.registrationDate(),
                addressEntity);
        clientRepository.save(client);

        return new ClientResponse(
                client.getId(),
                client.getClientName(),
                client.getClientSurname(),
                client.getBirthday(),
                client.getGender(),
                client.getRegistrationDate(),
                client.getAddress().getId());
    }
    public Optional<ClientResponse> updateClientAddress(Long id, AddressRequest request) {
        Optional<AddressEntity> addressEntity = addressService.getAddressByAllField(request.country(), request.city(), request.street());

        if (addressEntity.isEmpty()) {
            Long idd = addressService.addAddress(request).id();
            addressEntity = addressService.getAddressEntityById(idd);
        }

        Optional<AddressEntity> finalAddressEntity = addressEntity;
        return clientRepository.findById(id).map(client -> {
            if (finalAddressEntity.isPresent()) {
                client.setAddress(finalAddressEntity.get());
            }
            client = clientRepository.save(client);
            return new ClientResponse(
                    client.getId(),
                    client.getClientName(),
                    client.getClientSurname(),
                    client.getBirthday(),
                    client.getGender(),
                    client.getRegistrationDate(),
                    client.getAddress().getId());
        });
    }

    /////////////////////////////////по заданию не нужно
    public Optional<ClientEntity> findById(Long id) {
        return clientRepository.findById(id);
    }
}
