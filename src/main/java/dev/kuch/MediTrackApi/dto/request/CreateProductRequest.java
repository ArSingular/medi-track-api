package dev.kuch.MediTrackApi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Artur Korol
 */

@Data
public class CreateProductRequest {

    @NotBlank(message = "Product name can't be empty")
    @NotNull(message = "Product name can't be empty")
    private String name;

    private String description;

    @NotBlank(message = "Specify the unit of measurement")
    private String unit;

    @NotNull(message = "Enter the minimum balance")
    @Min(value = 5, message = "The threshold cannot be negative")
    private Integer minQuantityThreshold;

}
