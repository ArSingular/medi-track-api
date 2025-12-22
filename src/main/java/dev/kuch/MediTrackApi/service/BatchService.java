package dev.kuch.MediTrackApi.service;

import dev.kuch.MediTrackApi.dto.request.RestockRequest;
import dev.kuch.MediTrackApi.dto.request.UsageRequest;
import dev.kuch.MediTrackApi.dto.response.TransactionResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Korol Artur
 * 22.12.2025
 */

@Service
public interface BatchService {

    void restockProduct(UUID clinicId, RestockRequest restockRequest);
    void useProduct(UUID clinicId, UsageRequest usageRequest);
    List<TransactionResponse> getClinicTransactionHistory(UUID clinicId);
    List<TransactionResponse> getProductTransactionHistory(UUID clinicId, UUID productId);


}
