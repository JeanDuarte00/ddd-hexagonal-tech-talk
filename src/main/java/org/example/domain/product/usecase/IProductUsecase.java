package org.example.domain.product.usecase;

import org.example.domain.product.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface IProductUsecase {
	UUID create (Product product);

	List<Product> getAll (Integer page);

	Product get (UUID id);

	void delete (UUID id);

	void changePrice(UUID id, BigDecimal price);
}