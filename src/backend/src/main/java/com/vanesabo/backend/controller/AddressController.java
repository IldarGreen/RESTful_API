package com.vanesabo.backend.controller;

import com.vanesabo.backend.request.AddressRequest;
import com.vanesabo.backend.response.AddressResponse;
import com.vanesabo.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

//    не мапим это
//    @PostMapping //не мапим
//    public ResponseEntity<AddressResponse> addAddress(@RequestBody AddressRequest request) {
//        return new ResponseEntity<>(addressService.addAddress(request), HttpStatus.OK);
//    }

    @GetMapping()
    public ResponseEntity<List<AddressResponse>> getAllAddreses() {

        return new ResponseEntity<>(addressService.getAllAddreses(), HttpStatus.OK);
    }

}
