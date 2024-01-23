package org.example.domain.product.repository;

import org.example.domain.product.model.Product;
import org.example.infrastructure.repository.postgres.IPostgresRepository;
import org.example.infrastructure.repository.postgres.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component("PostgresDatabase")
public class ProductPostgresRepository implements IProductRepository {

	private final IPostgresRepository repository;

	@Autowired
	public ProductPostgresRepository (IPostgresRepository productRepository) {
		this.repository = productRepository;
	}
	@Override
	public List<Product> getAll (PageRequest pageRequest) {
		return repository.findAll(pageRequest).stream().map(this::entityToDomain).toList();
	}

	@Override
	public Optional<Product> findById (UUID id) {
		return repository.findById(id).map(this::entityToDomain);
	}

	@Override
	public UUID save (Product object) {
		ProductEntity saved = repository.save(this.domainToEntity(object));
		return saved.getId();
	}

	@Override
	public void delete (UUID id) {
		repository.deleteById(id);
	}

	private Product entityToDomain(ProductEntity entity){
		return new Product(entity.getId(), entity.getPrice(), entity.getName());
	}
	private ProductEntity domainToEntity(Product domain){
		return new ProductEntity(domain.getId(), domain.getPrice(), domain.getName());
	}
}
