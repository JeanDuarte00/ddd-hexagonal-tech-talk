package org.example.application.product.entrypoint;

import org.example.application.product.request.ChangePriceProductRequest;
import org.example.application.product.request.CreateNewProductRequest;
import org.example.domain.product.api.IProductAPI;
import org.example.domain.product.model.Product;
import org.example.domain.product.usecase.IProductUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductEntrypoint implements IProductAPI<UUID> {
	private IProductUsecase usecase;

	@Autowired
	public ProductEntrypoint (IProductUsecase usecase) {
		this.usecase = usecase;
	}
	@Override
	@PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity get (@PathVariable UUID id) {
		Product response = usecase.get(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getAll (@RequestParam Integer page) {
		List<Product> response = usecase.getAll(page);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UUID> create (@RequestBody CreateNewProductRequest request) {
		UUID response = usecase.create(new Product(request.price(), request.name()));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Override
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity delete (@PathVariable UUID id) {
		usecase.delete(id);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Override
	@PatchMapping(value = "/price", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity changePrice (@RequestBody ChangePriceProductRequest request) {
		usecase.changePrice(request.id(), request.price());
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
