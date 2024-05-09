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
//    @DeleteMapping("/{id}")
//    @DeleteMapping("/delete/{id}")
    @GetMapping("/{id}/delete")
    public void deleteById(@PathVariable("id") Long id) {
        clientService.deleteById(id);
//        return "redirect:/client/2";
    }

    @GetMapping("/search{name}{surname}")
    public List<ClientEntity> getClientsByNameAndSurname(@PathVariable String name, @PathVariable String surname) {
//        System.out.println(name + " " + surname + "--------------------");
        return clientService.getClientsByNameAndSurname(name, surname);
    }
//    http://localhost:8080/clients/search?name=%D0%9A%D1%8D%D0%BB%D0%B5%D0%BD&surname=%D0%90%D0%BC%D0%BD%D0%B5%D0%BB%D0%BB

    ////////////////////////
    @GetMapping("/{id}")
    public Optional<ClientEntity> findById(@PathVariable("id") Long id) {
        return clientService.findById(id);
    }



};

