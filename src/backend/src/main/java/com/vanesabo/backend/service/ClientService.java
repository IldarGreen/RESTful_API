package com.vanesabo.backend.service;

import com.vanesabo.backend.model.ClientEntity;
import com.vanesabo.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

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
