package org.example.application.rent.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.example.domain.product.model.Product;

public class AddProductRequest {
	@NotNull
	private Product product;

	@JsonCreator
	public AddProductRequest (@JsonProperty("product") final Product product) {
		this.product = product;
	}

	public Product getProduct () {
		return product;
	}
}