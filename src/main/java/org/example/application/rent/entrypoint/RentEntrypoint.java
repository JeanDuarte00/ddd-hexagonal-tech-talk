package org.example.application.entrypoint;

import org.example.application.request.AddProductRequest;
import org.example.application.request.CreateRentRequest;
import org.example.application.response.CreateRentResponse;
import org.example.domain.rent.api.IRentAPI;
import org.example.domain.rent.model.Rent;
import org.example.domain.rent.usecase.IRentService;
import org.example.domain.rent.usecase.RentService;
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
	private IRentService rentService;

	@Autowired
	public RentEntrypoint (RentService orderService) {
		this.rentService = orderService;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateRentResponse> create(@RequestBody CreateRentRequest request) {
		UUID response = rentService.createRent(request.getProducts());
		return ResponseEntity.status(HttpStatus.CREATED).body(new CreateRentResponse(response));
	}

	@Override
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity get (UUID id) {
		Rent response = rentService.get(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Rent>> getAll(@RequestParam Integer page) {
		List response = rentService.getAll(page);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping(value = "/{id}/product", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addProduct(@PathVariable UUID id, @RequestBody AddProductRequest request) {
		rentService.addProduct(id, request.getProduct());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping(value = "/{id}/product")
	public ResponseEntity deleteProduct(@PathVariable UUID id, @RequestParam UUID itemId) {
		rentService.deleteProduct(id, itemId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity delete (UUID id) {
		rentService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/{id}/complete")
	public ResponseEntity completeOrder(@PathVariable UUID id) {
		rentService.completeRent(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}