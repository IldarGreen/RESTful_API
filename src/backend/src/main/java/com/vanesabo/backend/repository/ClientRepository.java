package com.vanesabo.backend.repository;

import com.vanesabo.backend.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {
    List<ClientEntity> findByClientNameAndClientSurname(String clientName,
                                                        String clientSurname);
}
