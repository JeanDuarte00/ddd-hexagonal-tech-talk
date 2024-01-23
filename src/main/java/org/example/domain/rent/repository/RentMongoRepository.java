package org.example.domain.rent.repository;

import org.example.domain.rent.model.Rent;
import org.example.infrastructure.repository.mongo.IRentMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component("MongoDatabase")
public class RentMongoRepository implements IRentRepository {

	private final IRentMongoRepository repository;
	@Autowired
	public RentMongoRepository (IRentMongoRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(Rent order) {
		repository.save(order);
	}

	@Override
	public void delete (UUID id) {
		repository.deleteById(id);
	}

	@Override
	public List<Rent> getAll (PageRequest pageRequest) {
		return repository.findAll(pageRequest).toList();
	}

	@Override
	public Optional<Rent> findById (UUID id) {
		return repository.findById(id);
	}
}