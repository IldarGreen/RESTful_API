package com.vanesabo.backend.service;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.model.ClientEntity;
import com.vanesabo.backend.repository.ClientRepository;
import com.vanesabo.backend.request.AddressRequest;
import com.vanesabo.backend.request.ClientRequest;
import com.vanesabo.backend.response.ClientResponse;
import com.vanesabo.backend.utils.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.vanesabo.backend.utils.ClientMapper.clientEntityToClientResponse;
import static com.vanesabo.backend.utils.ClientMapper.clientRequestToClientEntity;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressService addressService;

    public ClientResponse addClient(ClientRequest clientRequest) {
        AddressEntity addressEntity = addressService.getAddressById(clientRequest.addressId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Address with id: " + clientRequest.addressId() + " not found"));
        ClientEntity clientEntity = clientRequestToClientEntity(clientRequest, addressEntity);
        clientRepository.save(clientEntity);

        return clientEntityToClientResponse(clientEntity);
    }

    public void deleteById(UUID id) {
        ClientEntity clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Client with id: " + id + " not found"));
        clientRepository.deleteById(clientEntity.getId());
    }

    public List<ClientResponse> getClientsByNameAndSurname(String name, String surname) {
        List<ClientEntity> clientEntity = clientRepository.findByClientNameAndClientSurname(name, surname);
        return clientEntity
                .stream()
                .map(ClientMapper::clientEntityToClientResponse)
                .toList();
    }

    public Optional<ClientResponse> updateClientAddress(UUID clientId, AddressRequest request) {
        Optional<AddressEntity> finalAddressEntity = addressService.addressAddPrepare(request, addressService);
        return clientRepository.findById(clientId).map(clientEntity -> {
            finalAddressEntity.ifPresent(clientEntity::setAddress);
            clientRepository.save(clientEntity);
            return clientEntityToClientResponse(clientEntity);
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

        return clients
                .stream()
                .map(ClientMapper::clientEntityToClientResponse)
                .toList();
    }

    public Optional<ClientEntity> findById(UUID id) {
        return clientRepository.findById(id);
    }
}
