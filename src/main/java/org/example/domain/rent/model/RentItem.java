package org.example.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class RentItem {

	private UUID rentId;
	private BigDecimal price;

	public RentItem (Product product) {
		this.rentId = product.getId();
		this.price = product.getPrice();
	}
}
