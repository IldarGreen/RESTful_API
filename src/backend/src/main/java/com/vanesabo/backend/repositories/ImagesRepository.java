package com.vanesabo.backend.repositories;

import com.vanesabo.backend.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ImagesRepository extends CrudRepository<ClientEntity, UUID> {
}
