package com.vanesabo.backend.repository;

import com.vanesabo.backend.model.ImagesEntity;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImagesRepository extends JpaRepository<ImagesEntity, UUID> {
    @Query("SELECT i FROM ImagesEntity i JOIN i.products p WHERE p.id = :productId")
    List<ImagesEntity> findByProductsId(@Param("productId") UUID productId);
}
