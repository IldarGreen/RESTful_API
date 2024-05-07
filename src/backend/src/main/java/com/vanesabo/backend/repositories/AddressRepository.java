package com.vanesabo.backend.repositories;

import com.vanesabo.backend.entities.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AddressRepository extends CrudRepository<AddressEntity, UUID> {
}
