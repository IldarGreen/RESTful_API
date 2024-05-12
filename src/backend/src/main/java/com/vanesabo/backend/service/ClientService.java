package com.vanesabo.backend.service;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.model.ClientEntity;
import com.vanesabo.backend.repository.ClientRepository;
import com.vanesabo.backend.request.AddressRequest;
import com.vanesabo.backend.request.ClientRequest;
import com.vanesabo.backend.response.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Optional<ClientResponse> updateClientAddress(Long clientId, AddressRequest request) {
        Optional<AddressEntity> addressEntity = addressService.getAddressByAllField(request.country(), request.city(), request.street());

        if (addressEntity.isEmpty()) {
            Long addressId = addressService.addAddress(request).id();
            addressEntity = addressService.getAddressEntityById(addressId);
        }

        Optional<AddressEntity> finalAddressEntity = addressEntity;
        return clientRepository.findById(clientId).map(client -> {
            if (finalAddressEntity.isPresent()) {
                client.setAddress(finalAddressEntity.get());
            }
//            client = clientRepository.save(client);
            clientRepository.save(client);
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

    public List<ClientResponse> getAllClientsPaginated(Integer limit, Integer offset) {
        List<ClientEntity> clients;
        if (limit == null || offset == null) {
            clients = clientRepository.findAll();
        } else {

            Pageable pageable = PageRequest.of(offset, limit);
            Page<ClientEntity> clientPage = clientRepository.findAll(pageable);
            clients = clientPage.getContent();
        }

        return clients.stream()
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

    public Optional<ClientEntity> findById(Long id) {
        return clientRepository.findById(id);
    }
}
