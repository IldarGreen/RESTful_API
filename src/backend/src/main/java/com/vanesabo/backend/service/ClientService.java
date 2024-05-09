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

    public ClientEntity save(ClientEntity clientEntity) {
        return clientRepository.save(clientEntity);
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

//    public void deleteById(Long id) {
//        ClientEntity clientEntity = clientRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        clientRepository.deleteById(clientEntity.getId());
//    }


//    public void deleteById(UUID id) {
//        ClientEntity clientEntity = clientRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                        "Client not found with id: " + id));
//        clientRepository.deleteById(clientEntity.getId());
//    }


    public List<ClientEntity> getClientsByNameAndSurname(String name, String surname) {
        return clientRepository.getClientsByNameAndSurname(name, surname);
    }

    /////////////////////////////////
    public Optional<ClientEntity> findById(Long id) {
        return clientRepository.findById(id);
    }
}
