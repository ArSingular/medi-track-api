package dev.kuch.MediTrackApi.service;

import dev.kuch.MediTrackApi.dto.request.CreateProductRequest;
import dev.kuch.MediTrackApi.dto.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Korol Artur
 * 22.12.2025
 */

@Service
public interface ProductService {
    ProductResponse createProduct(UUID clinicId, CreateProductRequest createProductRequest);
    List<ProductResponse> getAllProducts(UUID clinicId);

}
