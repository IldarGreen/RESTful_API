package com.vanesabo.backend.service;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;


    public List<AddressEntity> findAll() {
        return addressRepository.findAll();
    }

    public Optional<AddressEntity> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public AddressEntity getAddressEntityById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Address not found with id: " + id));
    }

//    public Book save(Book book) {
//        return bookRepository.save(book);
//    }
//
//    public void deleteById(Long id) {
//        bookRepository.deleteById(id);
//    }
//
//    public List<Book> findByTitle(String title) {
//        return bookRepository.findByTitle(title);
//    }
//
//    public List<Book> findByPublishedDateAfter(LocalDate date) {
//        return bookRepository.findByPublishedDateAfter(date);
//    }

}
