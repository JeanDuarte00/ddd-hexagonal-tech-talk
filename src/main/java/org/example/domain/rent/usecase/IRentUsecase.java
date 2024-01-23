package org.example.domain.rent.usecase;

import org.example.domain.product.model.Product;
import org.example.domain.rent.model.Rent;

import java.util.List;
import java.util.UUID;

public interface IRentUsecase {
	UUID createRent (List<Product> product);

	void addProduct (UUID id, Product product);

	List<Rent> getAll (Integer page);

	void deleteProduct (UUID id, UUID productId);

	void completeRent (UUID id);

	Rent get (UUID id);

	void delete (UUID id);
}
