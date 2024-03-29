package org.example.domain.product.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class Product {

	private UUID id;
	private BigDecimal price;
	private String name;

	public Product(BigDecimal price, String name) {
		this(UUID.randomUUID(), price, name);
	}
	@JsonCreator
	public Product(@JsonProperty("id") UUID id, @JsonProperty("price") BigDecimal price, @JsonProperty("name") String name) {
		this.id = id;
		this.price = price;
		this.name = name;
	}

	public Product changePrice(BigDecimal newPrice){
		this.price = newPrice;
		return this;
	}
}
