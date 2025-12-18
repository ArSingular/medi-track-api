package dev.kuch.MediTrackApi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Artur Korol
 */

@Data
public class CreateProductRequest {

    @NotBlank(message = "Product name can't be empty")
    private String name;

}
