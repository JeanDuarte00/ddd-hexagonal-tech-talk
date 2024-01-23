package org.example.application.rent.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.example.domain.product.model.Product;

import java.util.List;

public class CreateRentRequest {
	@NotNull
	private List<Product> productList;

	@JsonCreator
	public CreateRentRequest(@JsonProperty("productList") @NotNull final List<Product> productList) {
		this.productList = productList;
	}

	public List<Product> getProducts() {
		return productList;
	}
}