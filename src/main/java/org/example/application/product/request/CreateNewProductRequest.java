package org.example.application.product.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateNewProductRequest (@NotNull String name, @NotNull BigDecimal price){}
