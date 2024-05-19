package com.vanesabo.backend.repository;

import com.vanesabo.backend.model.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<SupplierEntity, UUID> {
}
