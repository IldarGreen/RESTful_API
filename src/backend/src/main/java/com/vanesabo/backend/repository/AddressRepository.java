package com.vanesabo.backend.repository;

import com.vanesabo.backend.model.AddressEntity;
import com.vanesabo.backend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

//    List<Book> findByTitle(String title);
//      Optional<AddressEntity> getAddressByAllField(String country, String city, String street);

    @Query("SELECT a FROM AddressEntity a WHERE a.country = :country AND a.city = :city AND a.street = :street")
    Optional<AddressEntity> getAddressByAllField(@Param("country") String country,
                                                 @Param("city") String city,
                                                 @Param("street") String street);

}
