package dev.kuch.MediTrackApi.dto.response;

import lombok.Data;

import java.util.UUID;

/**
 * @author Korol Artur
 * 19.12.2025
 */

@Data
public class SupplierResponse {

    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String contactPerson;

}
