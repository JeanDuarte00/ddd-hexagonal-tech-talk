package org.example.infrastructure.repository.postgres.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.product.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity(name = "product")
public class ProductEntity {

	@Id
	private UUID id;
	private BigDecimal price;
	private String name;

	public ProductEntity(Product product) {
		this(product.getId(), product.getPrice(), product.getName());
	}
	public ProductEntity(UUID id, BigDecimal price, String name) {
		this.id = id;
		this.price = price;
		this.name = name;
	}
}