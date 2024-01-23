package org.example.domain.rent.api;

import org.example.application.rent.request.AddProductToRentRequest;
import org.example.application.rent.request.CreateRentRequest;
import org.example.domain.api.IEntrypointAPI;
import org.example.domain.rent.model.Rent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface IRentAPI<I> extends IEntrypointAPI<I, Rent> {
	ResponseEntity<I> create(@RequestBody CreateRentRequest request);
	ResponseEntity addProduct(@PathVariable I id, @RequestBody AddProductToRentRequest request);
	ResponseEntity deleteProduct(@PathVariable I id, @RequestParam I itemId);
	ResponseEntity completeOrder(@PathVariable I id);
}
