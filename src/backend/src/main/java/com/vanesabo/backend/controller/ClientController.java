package com.vanesabo.backend.controller;

import com.vanesabo.backend.model.Book;
import com.vanesabo.backend.model.ClientEntity;
import com.vanesabo.backend.request.AddressRequest;
import com.vanesabo.backend.request.ClientRequest;
import com.vanesabo.backend.response.AddressResponse;
import com.vanesabo.backend.response.ClientResponse;
import com.vanesabo.backend.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> addClient(@RequestBody ClientRequest request) {
        ClientResponse newClient = clientService.addClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/{id}/delete")
//    @DeleteMapping("/{id}/delete")///////////////////////////////////////
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClientResponse>> getClientsByNameAndSurname(
            @RequestParam String name,
            @RequestParam String surname) {
        List<ClientResponse> clients = clientService.getClientsByNameAndSurname(name, surname);
        return ResponseEntity.ok(clients);
    }

    //4. Получение всех клиентов (В данном запросе необходимо предусмотреть опциональные параметры
    // пагинации в строке запроса: limit и offset). В случае отсутствия эти параметров возвращать весь список.
    @GetMapping
    ResponseEntity<List<ClientResponse>> getAllClientsPaginated(
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer offset) {
        List<ClientResponse> clients = clientService.getAllClientsPaginated(limit, offset);
        return ResponseEntity.ok(clients);
    }

    //

//    @PatchMapping("/{id}/address")
//    public ResponseEntity<ClientResponse> updateClientAddress(
//            @PathVariable Long id,
//            @RequestBody Long newAddress) {
//        return clientService.updateClientAddress(id, newAddress)
//                .map(ResponseEntity::ok)
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
    @PatchMapping("/{id}/address")
    public ResponseEntity<ClientResponse> updateClientAddress(
//    public void updateClientAddress(
//    public void updateClientAddress(
            @PathVariable Long id,
            @RequestBody AddressRequest request) {
        return clientService.updateClientAddress(id, request)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        clientService.updateClientAddress(id, request);
    }


    @GetMapping("/{id}")
//    public Optional<ClientEntity> findById(@PathVariable("id") @Valid Long id) {
    public Optional<ClientEntity> findById(@Valid @PathVariable("id") Long id) {
        return clientService.findById(id);
    }

}
