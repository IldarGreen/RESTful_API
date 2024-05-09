package com.vanesabo.backend.service;

import com.vanesabo.backend.model.ClientEntity;
import com.vanesabo.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        clientRepository.deleteById(id);
    }


    public List<ClientEntity> getClientsByNameAndSurname(String name, String surname) {
        return clientRepository.getClientsByNameAndSurname(name, surname);
    }

    /////////////////////////////////
    public Optional<ClientEntity> findById(Long id) {
        return clientRepository.findById(id);
    }
}
