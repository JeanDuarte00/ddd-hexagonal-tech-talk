package org.example.domain.rent.usecase;

import org.example.domain.exception.NotFoundException;
import org.example.domain.product.model.Product;
import org.example.domain.rent.model.Rent;
import org.example.domain.rent.repository.IRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RentUsecase implements IRentUsecase {

	private final IRentRepository orderRepository;
	private final Integer pageSize;

	@Autowired
	public RentUsecase (IRentRepository orderRepository, @Value("${application.order.page-size}") Integer size) {
		this.orderRepository = orderRepository;
		this.pageSize = size;
	}

	@Override
	public UUID createRent (List<Product> productList) {
		Rent order = new Rent(productList);
		orderRepository.save(order);

		return order.getId();
	}

	@Override
	public void addProduct (UUID id, Product product) {
		Rent order = getRent(id);
		order.addOrder(product);

		orderRepository.save(order);
	}

	@Override
	public void completeRent (UUID id) {
		Rent order = getRent(id);
		order.complete();

		orderRepository.save(order);
	}

	@Override
	public Rent get (UUID id) {
		return this.getRent(id);
	}

	@Override
	public List<Rent> getAll (Integer page) {
		var pageRequest = PageRequest.of(page, pageSize);
		return orderRepository.getAll(pageRequest);
	}

	@Override
	public void deleteProduct (UUID orderId, UUID itemId) {
		Rent order = getRent(orderId);
		order.removeItemFromOrder(itemId);
		orderRepository.save(order);
	}

	@Override
	public void delete (UUID id) {
		orderRepository.delete(id);
	}

	private Rent getRent (UUID id) {
		return orderRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not find Rent"));
	}
}