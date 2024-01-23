package org.example.application.rent.entrypoint;

import org.example.application.rent.request.AddProductToRentRequest;
import org.example.application.rent.request.CreateRentRequest;
import org.example.domain.rent.api.IRentAPI;
import org.example.domain.rent.model.Rent;
import org.example.domain.rent.usecase.IRentUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rents")
public class RentEntrypoint implements IRentAPI<UUID> {
	private IRentUsecase usecase;

	@Autowired
	public RentEntrypoint (IRentUsecase usecase) {
		this.usecase = usecase;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UUID> create(@RequestBody CreateRentRequest request) {
		UUID response = usecase.createRent(request.getProducts());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Override
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity get (@PathVariable UUID id) {
		Rent response = usecase.get(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Rent>> getAll(@RequestParam Integer page) {
		List response = usecase.getAll(page);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping(value = "/{id}/product", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addProduct(@PathVariable UUID id, @RequestBody AddProductToRentRequest request) {
		usecase.addProduct(id, request.getProduct());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping(value = "/{id}/product")
	public ResponseEntity deleteProduct(@PathVariable UUID id, @RequestParam UUID itemId) {
		usecase.deleteProduct(id, itemId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity delete (@PathVariable UUID id) {
		usecase.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/{id}/complete")
	public ResponseEntity completeOrder(@PathVariable UUID id) {
		usecase.completeRent(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}