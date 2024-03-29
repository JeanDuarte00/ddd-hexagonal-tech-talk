package org.example.application.product.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ChangePriceProductRequest(@NotNull UUID id, @NotNull BigDecimal price) {
}
