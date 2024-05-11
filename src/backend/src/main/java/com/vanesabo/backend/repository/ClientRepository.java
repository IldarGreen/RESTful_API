package com.vanesabo.backend.repository;

import com.vanesabo.backend.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    List<ClientEntity> findByClientNameAndClientSurname(String clientName,
                                                        String clientSurname);
}
