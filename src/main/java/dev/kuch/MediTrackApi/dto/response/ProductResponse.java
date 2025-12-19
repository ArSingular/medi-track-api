package dev.kuch.MediTrackApi.dto.response;

import lombok.Data;

import java.util.UUID;

/**
 * @author Korol Artur
 * 19.12.2025
 */

@Data
public class ProductResponse {

    private UUID id;
    private String name;
    private String description;
    private String unit;
    private Integer minQuantityThreshold;

    private Integer totalQuantity;


}
