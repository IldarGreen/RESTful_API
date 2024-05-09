package com.vanesabo.backend.repository;

import com.vanesabo.backend.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
//
//    List<Book> findByTitle(String title);
//
//    // Custom query
//    @Query("SELECT b FROM Book b WHERE b.publishDate > :date")
//    List<Book> findByPublishedDateAfter(@Param("date") LocalDate date);

}
