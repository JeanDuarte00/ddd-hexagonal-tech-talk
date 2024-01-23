package org.example.domain.rent.repository;

import org.example.domain.rent.model.Rent;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRentRepository {
	List<Rent> getAll (PageRequest pageRequest);
	Optional<Rent> findById(UUID id);
	void save(Rent order);
	void delete (UUID id);
}