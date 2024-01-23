package org.example.infrastructure.repository.postgres;

import org.example.infrastructure.repository.postgres.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPostgresRepository extends JpaRepository<ProductEntity, UUID> {
}
