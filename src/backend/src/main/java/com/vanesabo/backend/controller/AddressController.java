package com.vanesabo.backend.controller;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<AddressEntity> findAll() {
        return addressService.findAll();
    }


}
