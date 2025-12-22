package dev.kuch.MediTrackApi.service.impl;

import dev.kuch.MediTrackApi.dto.request.CreateSupplierRequest;
import dev.kuch.MediTrackApi.dto.response.SupplierResponse;
import dev.kuch.MediTrackApi.entity.Clinic;
import dev.kuch.MediTrackApi.entity.Supplier;
import dev.kuch.MediTrackApi.repository.ClinicRepository;
import dev.kuch.MediTrackApi.repository.SupplierRepository;
import dev.kuch.MediTrackApi.service.SupplierService;
import dev.kuch.MediTrackApi.util.mapper.SupplierMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author Korol Artur
 * 19.12.2025
 */

@Service
@AllArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ClinicRepository clinicRepository;
    private final SupplierMapper supplierMapper;

    @Transactional
    public SupplierResponse createSupplier(UUID clinicId, CreateSupplierRequest createSupplierRequest){
        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new EntityNotFoundException(" Clinic not found "));

        if(supplierRepository.existsByNameAndClinicId(createSupplierRequest.getName(), clinicId)){
            throw new IllegalArgumentException("A provider with that name already exists");
        }

        Supplier supplier = supplierMapper.toSupplier(createSupplierRequest);
        supplier.setClinic(clinic);

        Supplier saved = supplierRepository.save(supplier);

        return supplierMapper.toSupplierResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<SupplierResponse> getAllSuppliers (UUID clinicId){
        return supplierRepository.findAllByClinicId(clinicId)
                .stream().map(supplierMapper::toSupplierResponse)
                .toList();
    }

}
