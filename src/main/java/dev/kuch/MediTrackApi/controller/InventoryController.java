package dev.kuch.MediTrackApi.controller;

import dev.kuch.MediTrackApi.dto.request.RestockRequest;
import dev.kuch.MediTrackApi.dto.request.UsageRequest;
import dev.kuch.MediTrackApi.dto.response.TransactionResponse;
import dev.kuch.MediTrackApi.service.BatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author Artur Korol
 */

@RestController
@RequestMapping("/api/clinics/{clinicId}/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final BatchService batchService;

    @PostMapping("/restock")
    public ResponseEntity<String> restockProduct(@PathVariable UUID clinicId,
                                                 @Valid @RequestBody RestockRequest restockRequest) {

        batchService.restockProduct(clinicId, restockRequest);

        return ResponseEntity.ok("Restock Success");
    }

    @PostMapping("/usage")
    public ResponseEntity<String> useProduct(@PathVariable UUID clinicId,
                                                 @Valid @RequestBody UsageRequest usageRequest) {

        batchService.useProduct(clinicId, usageRequest);

        return ResponseEntity.ok("Product usage success");
    }

    @GetMapping("/history")
    public ResponseEntity<List<TransactionResponse>> getFullHistory(@PathVariable UUID clinicId){
        return ResponseEntity.ok(batchService.getClinicTransactionHistory(clinicId));
    }

    @GetMapping("/history/{productId}")
    public ResponseEntity<List<TransactionResponse>> getProductHistory(@PathVariable UUID clinicId,
                                                                       @PathVariable UUID productId){
        return ResponseEntity.ok(batchService.getProductTransactionHistory(clinicId, productId));
    }

}
