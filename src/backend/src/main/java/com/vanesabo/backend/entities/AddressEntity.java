package com.vanesabo.backend.entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import javax.persistence.*;

import java.util.UUID;

@EntityScan
@Table(name = "address")
public class AddressEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
}
