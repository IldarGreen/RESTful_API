package com.vanesabo.backend.repositories;

import com.vanesabo.backend.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<ProductEntity, UUID> {
}
