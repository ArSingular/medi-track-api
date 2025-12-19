package dev.kuch.MediTrackApi.controller;

import dev.kuch.MediTrackApi.dto.request.CreateProductRequest;
import dev.kuch.MediTrackApi.dto.response.ProductResponse;
import dev.kuch.MediTrackApi.service.ProductService;
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
@RequestMapping("/api/clinics/{clinicId}/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @PathVariable UUID clinicId,
            @Valid @RequestBody CreateProductRequest createProductRequest
            ){
        ProductResponse response = productService.createProduct(clinicId, createProductRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(@PathVariable UUID clinicId){
        return ResponseEntity.ok(productService.getAllProducts(clinicId));
    }

}
