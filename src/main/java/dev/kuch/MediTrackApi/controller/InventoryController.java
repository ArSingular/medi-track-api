package dev.kuch.MediTrackApi.controller;

import dev.kuch.MediTrackApi.dto.request.RestockRequest;
import dev.kuch.MediTrackApi.dto.request.UsageRequest;
import dev.kuch.MediTrackApi.service.BatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                                 @RequestParam UUID userId,
                                                 @Valid @RequestBody RestockRequest restockRequest) {

        batchService.restockProduct(clinicId, userId, restockRequest);

        return ResponseEntity.ok("Restock Success");
    }

    @PostMapping("/usage")
    public ResponseEntity<String> useProduct(@PathVariable UUID clinicId,
                                                 @RequestParam UUID userId,
                                                 @Valid @RequestBody UsageRequest usageRequest) {

        batchService.useProduct(clinicId, userId, usageRequest);

        return ResponseEntity.ok("Product usage success");
    }

}
