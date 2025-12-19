package dev.kuch.MediTrackApi.service;

import dev.kuch.MediTrackApi.dto.request.CreateProductRequest;
import dev.kuch.MediTrackApi.dto.response.ProductResponse;
import dev.kuch.MediTrackApi.entity.Clinic;
import dev.kuch.MediTrackApi.entity.Product;
import dev.kuch.MediTrackApi.repository.BatchRepository;
import dev.kuch.MediTrackApi.repository.ClinicRepository;
import dev.kuch.MediTrackApi.repository.ProductRepository;
import dev.kuch.MediTrackApi.util.mapper.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Korol Artur
 * 19.12.2025
 */

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ClinicRepository clinicRepository;
    private final BatchRepository batchRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductResponse createProduct(UUID clinicId, CreateProductRequest createProductRequest){
        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));

        if(productRepository.existsByNameAndClinicId(createProductRequest.getName(), clinicId)){
            throw new IllegalArgumentException("Product with this name already exists in this clinic");
        }

        Product product = productMapper.toProduct(createProductRequest);
        product.setClinic(clinic);

        Product saved = productRepository.save(product);

        return productMapper.toProductResponse(saved, 0);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts(UUID clinicId){
        List<Product> products = productRepository.findAllByClinicId(clinicId);

        return products.stream()
                .map(product -> {
                    Integer totalQuantity = batchRepository.getTotalQuantity(product.getId(), clinicId);
                    int finalQuantity = (totalQuantity == null) ? 0 : totalQuantity;

                    return productMapper.toProductResponse(product, finalQuantity);
                }).collect(Collectors.toList());
    }

}
