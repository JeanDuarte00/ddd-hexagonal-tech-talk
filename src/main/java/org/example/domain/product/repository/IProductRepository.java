package org.example.domain.product.repository;

import org.example.domain.product.model.Product;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductRepository {
	List<Product> getAll (PageRequest pageRequest);
	Optional<Product> findById(UUID id);
	UUID save(Product order);
	void delete (UUID id);
}
