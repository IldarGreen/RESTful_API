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

    //1
    @PostMapping
    @Operation(summary = "Add a new client", description = "Adding a new client to the database.")
    public ResponseEntity<ClientResponse> addClient(@Valid @RequestBody ClientRequest request) {
        ClientResponse newClient = clientService.addClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    //2
    //    @GetMapping("/{id}/delete")
//    @Operation(summary = "Delete client by ID", description = "Delete a client with a given identifier")
//    public ResponseEntity<Void> deleteClientById(@PathVariable Long id) {
//        clientService.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
    //2
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete client by ID", description = "Delete a client with a given identifier")
    public void deleteClientById(@PathVariable Long id) {
        clientService.deleteById(id);
    }

    //3
    @GetMapping("/search")
    @Operation(summary = "Get clients by name and surname", description = "We are looking for clients by their name and last surname.")
    public ResponseEntity<List<ClientResponse>> getClientsByNameAndSurname(
            @RequestParam String name,
            @RequestParam String surname) {
        return ResponseEntity.ok(clientService.getClientsByNameAndSurname(name, surname));
    }

    //4
    @GetMapping
    @Operation(summary = "Get all clients with pagination option", description = "Retrieves a list of clients with pagination option.")
    ResponseEntity<List<ClientResponse>> getAllClientsPaginated(
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer offset) {
        return ResponseEntity.ok(clientService.getAllClientsPaginated(limit, offset));
    }

    //5
    @PatchMapping("/{id}/address")
    @Operation(summary = "Update client's address", description = "Updates the address for the specified client.")
    public ResponseEntity<ClientResponse> updateClientAddress(
            @PathVariable Long id,
            @RequestBody AddressRequest request) {
        return clientService.updateClientAddress(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get clients by ID", description = "Get a client by a specific id")
    public Optional<ClientEntity> findById(@PathVariable("id") Long id) {
        return clientService.findById(id);
    }

}
