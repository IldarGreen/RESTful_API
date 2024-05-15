package com.vanesabo.backend.controller;

import com.vanesabo.backend.model.ClientEntity;
import com.vanesabo.backend.request.AddressRequest;
import com.vanesabo.backend.request.ClientRequest;
import com.vanesabo.backend.response.ClientResponse;
import com.vanesabo.backend.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clients")
@Tag(name = "Client", description = "Operations related to client")
public class ClientController {

    @Autowired
    private ClientService clientService;

////    @ResponseStatus(HttpStatus.CREATED) // 201
//    @PostMapping
//    @Operation(summary = "Add a new client", description = "Adding a new client to the database.")
//    public ResponseEntity<ClientResponse> addClient(@Valid @RequestBody ClientRequest request) {
//        ClientResponse newClient = clientService.addClient(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
////        return ResponseEntity.ok().body(newClient);
//    }

    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    @Operation(summary = "Add a new client", description = "Adding a new client to the database.")
    public ClientResponse addClient(@Valid @RequestBody ClientRequest request) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
        return clientService.addClient(request);
    }

    @GetMapping("/{id}/delete")
    @Operation(summary = "Delete client by ID", description = "Delete a client with a given identifier")
    public ResponseEntity<Void> deleteClientById(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Get clients by name and surname", description = "We are looking for clients by their name and last surname.")
    public ResponseEntity<List<ClientResponse>> getClientsByNameAndSurname(
            @RequestParam String name,
            @RequestParam String surname) {
        List<ClientResponse> clients = clientService.getClientsByNameAndSurname(name, surname);
        return ResponseEntity.ok(clients);
    }

    //4. Получение всех клиентов (В данном запросе необходимо предусмотреть опциональные параметры
    // пагинации в строке запроса: limit и offset). В случае отсутствия эти параметров возвращать весь список.
    @GetMapping
    @Operation(summary = "Get all clients with pagination option", description = "Retrieves a list of clients with pagination option.")
    ResponseEntity<List<ClientResponse>> getAllClientsPaginated(
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer offset) {
        List<ClientResponse> clients = clientService.getAllClientsPaginated(limit, offset);
        return ResponseEntity.ok(clients);
    }

    @PatchMapping("/{id}/address")
    @Operation(summary = "Update client's address", description = "Updates the address for the specified client.")
    public ResponseEntity<ClientResponse> updateClientAddress(
            @PathVariable Long id,
            @RequestBody AddressRequest request) {
        return clientService.updateClientAddress(id, request)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get clients by ID", description = "Get a client by a specific id")
    public Optional<ClientEntity> findById(@PathVariable("id") Long id) {
        return clientService.findById(id);
    }

}
