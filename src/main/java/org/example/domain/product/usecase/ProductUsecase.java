package org.example.domain.product.usecase;

import org.example.domain.exception.NotFoundException;
import org.example.domain.product.model.Product;
import org.example.domain.product.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductUsecase implements IProductUsecase{

	private final IProductRepository repository;
	private final Integer pageSize;

	@Autowired
	public ProductUsecase (IProductRepository productRepository, @Value("${application.order.page-size}") Integer size) {
		this.repository = productRepository;
		this.pageSize = size;
	}
	@Override
	public UUID create (Product product) {
		return repository.save(product);
	}

	@Override
	public List<Product> getAll (Integer page) {
		return repository.getAll(PageRequest.of(page, pageSize));
	}

	@Override
	public Product get (UUID id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Could not find product: " + id));
	}

	@Override
	public void delete (UUID id) {
		repository.delete(id);
	}

	@Override
	public void changePrice (UUID id, BigDecimal price) {
		Product product = this.get(id).changePrice(price);
		repository.save(product);
	}
}
