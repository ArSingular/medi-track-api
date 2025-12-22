package dev.kuch.MediTrackApi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Korol Artur
 * 22.12.2025
 */

@Data
public class RegisterRequest {

    @NotBlank(message = "Clinic name is required")
    private String clinicName;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+\\-=]).{6,}$",
            message = "Password must contain letters, numbers and a special symbol"
    )
    private String password;

}
