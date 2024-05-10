package com.vanesabo.backend.repository;

import com.vanesabo.backend.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

//    @Query("SELECT c FROM ClientEntity c WHERE c.client_name = :name AND c.client_surname = :surnam")

//    @Query("SELECT '*' FROM ClientEntity")
//    List<ClientEntity> getClientsByNameAndSurname(@Param("name") String clientName, @Param("surname") String clientSurname);

    List<ClientEntity> findByClientNameAndClientSurname(String clientName,
                                                        String clientSurname);
}
