package dev.kuch.MediTrackApi.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Artur Korol
 */

@Data
public class RestockRequest {

    @NotNull(message = "Product ID is required")
    private UUID productId;

    @NotNull(message = "Supplier ID is required")
    private UUID supplierId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Purchase price is required")
    @Min(value = 0, message = "Price cannot be negative")
    private BigDecimal purchasePrice;

    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in future")
    private LocalDate expiryDate;

    private String sku;

}
