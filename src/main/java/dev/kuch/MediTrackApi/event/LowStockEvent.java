package dev.kuch.MediTrackApi.event;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Korol Artur
 * 21.12.2025
 */

@Data
@AllArgsConstructor
public class LowStockEvent {

    private String clinicName;
    private String email;
    private String productName;
    private int currentQuantity;
    private int threshold;

}
