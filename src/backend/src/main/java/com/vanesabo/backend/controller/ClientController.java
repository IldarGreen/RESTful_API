package com.vanesabo.backend.controller;

import com.vanesabo.backend.model.Book;
import com.vanesabo.backend.model.ClientEntity;
import com.vanesabo.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    ///////////На вход подается JSON!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public ClientEntity createClient(@RequestBody ClientEntity clientEntity) {
        return clientService.save(clientEntity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }

    @GetMapping("/search")
    public List<ClientEntity> getClientsByNameAndSurname(@PathVariable String name, @PathVariable String surname) {
        return clientService.getClientsByNameAndSurname(name, surname);
    }

    ////////////////////////
    @GetMapping("/{id}")
    public Optional<ClientEntity> findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

};

