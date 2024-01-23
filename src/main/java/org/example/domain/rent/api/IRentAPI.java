package org.example.domain.api;

import org.example.application.request.AddProductRequest;
import org.example.application.request.CreateRentRequest;
import org.example.application.response.CreateRentResponse;
import org.example.domain.rent.model.Rent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface IRentAPI<I> extends IEntrypointAPI<I, Rent> {
	ResponseEntity<CreateRentResponse> create(@RequestBody CreateRentRequest request);
	ResponseEntity addProduct(@PathVariable I id, @RequestBody AddProductRequest request);
	ResponseEntity deleteProduct(@PathVariable I id, @RequestParam I itemId);
	ResponseEntity completeOrder(@PathVariable I id);
}
