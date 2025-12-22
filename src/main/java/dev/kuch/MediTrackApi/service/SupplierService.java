package dev.kuch.MediTrackApi.service;

import dev.kuch.MediTrackApi.dto.request.CreateSupplierRequest;
import dev.kuch.MediTrackApi.dto.response.SupplierResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Korol Artur
 * 22.12.2025
 */

@Service
public interface SupplierService {

    SupplierResponse createSupplier(UUID clinicId, CreateSupplierRequest createSupplierRequest);
    List<SupplierResponse> getAllSuppliers (UUID clinicId);

}
