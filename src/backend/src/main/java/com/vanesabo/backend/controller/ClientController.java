package com.vanesabo.backend.controller;

import com.vanesabo.backend.model.Book;
import com.vanesabo.backend.model.ClientEntity;
import com.vanesabo.backend.request.ClientRequest;
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


    //

    @PatchMapping("/{id}/address")
    public ResponseEntity<ClientResponse> updateClientAddress(
            @PathVariable Long id,
            @RequestBody Long newAddress) {
        return clientService.updateClientAddress(id, newAddress)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    ///////////////////////////////////////////////////////////////////
    ////////////////////////по заданию не нужна
    @GetMapping("/{id}")
//    public Optional<ClientEntity> findById(@PathVariable("id") @Valid Long id) {
    public Optional<ClientEntity> findById(@Valid @PathVariable("id") Long id) {
        return clientService.findById(id);
    }


//    ///////////На вход подается JSON!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    @ResponseStatus(HttpStatus.CREATED) // 201
//    @PostMapping
//    public ClientEntity createClient(@RequestBody ClientEntity clientEntity) {
//        return clientService.save(clientEntity);
//    }

//    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
////    @DeleteMapping("/{id}")
////    @DeleteMapping("/delete/{id}")
//    @GetMapping("/{id}/delete")
//    public void deleteById(@PathVariable("id") Long id) {
//        clientService.deleteById(id);
////        return "redirect:/client/2";
//    }



//    @GetMapping("/search{name}{surname}")
//    public List<ClientEntity> getClientsByNameAndSurname(@PathVariable String name, @PathVariable String surname) {
////        System.out.println(name + " " + surname + "--------------------");
//        return clientService.getClientsByNameAndSurname(name, surname);
//    }
//    http://localhost:8080/clients/search?name=%D0%9A%D1%8D%D0%BB%D0%B5%D0%BD&surname=%D0%90%D0%BC%D0%BD%D0%B5%D0%BB%D0%BB

};

