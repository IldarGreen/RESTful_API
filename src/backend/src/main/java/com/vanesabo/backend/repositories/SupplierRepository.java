package com.vanesabo.backend.repositories;

import com.vanesabo.backend.entities.SupplierEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SupplierRepository extends CrudRepository<SupplierEntity, UUID> {
}
