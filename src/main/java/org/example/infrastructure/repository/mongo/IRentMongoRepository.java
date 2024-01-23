package org.example.infrastructure.repository.mongo;

import org.example.domain.rent.model.Rent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRentMongoRepository extends MongoRepository<Rent, UUID> {
}
