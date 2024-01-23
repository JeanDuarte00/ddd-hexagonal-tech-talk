package org.example.domain.product.api;

import org.example.application.product.request.ChangePriceProductRequest;
import org.example.application.product.request.CreateNewProductRequest;
import org.example.domain.api.IEntrypointAPI;
import org.example.domain.product.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface IProductAPI<I> extends IEntrypointAPI<I, Product> {
	ResponseEntity<UUID> create(@RequestBody CreateNewProductRequest request);
	ResponseEntity changePrice (@RequestBody ChangePriceProductRequest request);
}