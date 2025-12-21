package dev.kuch.MediTrackApi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Korol Artur
 * 21.12.2025
 */

@Data
public class TransactionResponse {

    private UUID id;
    private String productName;
    private String userName;
    private String batchNumber;
    private String transactionType;
    private Integer quantityChange;
    private String reason;
    private LocalDateTime timestamp;

}
