package dev.kuch.MediTrackApi.controller;

import dev.kuch.MediTrackApi.dto.request.CreateSupplierRequest;
import dev.kuch.MediTrackApi.dto.response.SupplierResponse;
import dev.kuch.MediTrackApi.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author Korol Artur
 * 19.12.2025
 */

@RestController
@RequestMapping("/api/clinics/{clinicId}/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierResponse> createSupplier(@PathVariable UUID clinicId,
                                                           @Valid @RequestBody CreateSupplierRequest createSupplierRequest){

        SupplierResponse response = supplierService.createSupplier(clinicId, createSupplierRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAllSuppliers(@PathVariable UUID clinicId){
        return ResponseEntity.ok(supplierService.getAllSuppliers(clinicId));
    }


}
