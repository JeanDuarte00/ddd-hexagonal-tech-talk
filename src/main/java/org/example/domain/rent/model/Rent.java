package org.example.domain.rent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.exception.InvalidOperationException;
import org.example.domain.exception.NoOperationLeftException;
import org.example.domain.exception.NotFoundException;
import org.example.domain.product.model.Product;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class Rent {

	private UUID id;
	private RentStatus status;
	private List<RentItem> rentItems;
	private BigDecimal price;

	public Rent (UUID id, List<Product> product) {
		this(product);
		this.id = id;
	}
	public Rent (List<Product> productList) {
		this.id = UUID.randomUUID();
		this.status = RentStatus.CREATED;
		this.price = new BigDecimal(0);
		this.rentItems = new LinkedList<>();

		productList.parallelStream().forEach(product -> {
			this.price.add(product.getPrice());
			this.rentItems.add(new RentItem(product));
		});
	}

	public void complete() {
		validateState();
		this.status = RentStatus.COMPLETED;
	}

	public void addOrder(Product product) {
		validateState();
		validateProduct(product);
		rentItems.add(new RentItem(product));
		price = price.add(product.getPrice());
	}

	public void removeItemFromOrder (UUID id) {
		validateState();
		RentItem orderItem = getOrderItem(id);
		rentItems.remove(orderItem);

		price = price.subtract(orderItem.getPrice());
	}

	private RentItem getOrderItem(UUID id) {
		return rentItems.stream()
				.filter(orderItem -> orderItem.getRentId().equals(id))
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Product " + id + " is not in this Rent"));
	}

	private void validateState() {
		if (RentStatus.isCompleted(status)) {
			throw new NoOperationLeftException("This rent is already completed.");
		}
	}

	private void validateProduct(Product product) {
		if (Objects.isNull(product)) {
			throw new InvalidOperationException("Invalid product value.");
		}
	}
}