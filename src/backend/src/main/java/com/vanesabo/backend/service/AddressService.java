package com.vanesabo.backend.service;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

//
    public List<AddressEntity> findAll() {
        return addressRepository.findAll();
    }
//
//    public Optional<AddressEntity> findById(Long id) {
//        return addressRepository.findById(id);
//    }
//
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
