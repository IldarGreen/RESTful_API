package com.vanesabo.backend.repository;

import com.vanesabo.backend.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    @Query("SELECT a FROM AddressEntity a WHERE a.country = :country AND a.city = :city AND a.street = :street")
    Optional<AddressEntity> getAddressByAllField(@Param("country") String country,
                                                 @Param("city") String city,
                                                 @Param("street") String street);
}
