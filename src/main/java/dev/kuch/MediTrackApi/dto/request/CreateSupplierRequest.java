package dev.kuch.MediTrackApi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Korol Artur
 * 19.12.2025
 */

@Data
public class CreateSupplierRequest {

    @NotNull(message = "The name of the supplier is required")
    @NotBlank(message = "The name of the supplier is required")
    private String name;

    @Email(message = "Incorrect email format")
    private String email;

    private String phone;

    private String contactPerson;

}
