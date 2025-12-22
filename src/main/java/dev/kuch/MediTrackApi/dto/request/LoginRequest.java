package dev.kuch.MediTrackApi.dto.request;

import lombok.Data;

/**
 * @author Korol Artur
 * 22.12.2025
 */

@Data
public class LoginRequest {

    private String email;
    private String password;

}
