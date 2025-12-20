package dev.kuch.MediTrackApi.service;

import dev.kuch.MediTrackApi.dto.request.RestockRequest;
import dev.kuch.MediTrackApi.dto.request.UsageRequest;
import dev.kuch.MediTrackApi.entity.*;
import dev.kuch.MediTrackApi.repository.*;
import dev.kuch.MediTrackApi.util.mapper.BatchMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Artur Korol
 */

@Service
@RequiredArgsConstructor
public class BatchService {

    private final BatchRepository batchRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final ClinicRepository clinicRepository;
    private final InventoryTransactionRepository inventoryTransactionRepository;
    private final BatchMapper batchMapper;

    @Transactional
    public void restockProduct(UUID clinicId, UUID userId, RestockRequest restockRequest) {
        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));

        Product product = productRepository.findByIdAndClinicId(restockRequest.getProductId(), clinicId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found in this clinic"));

        Supplier supplier = supplierRepository.findByIdAndClinicId(restockRequest.getSupplierId(), clinicId)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

        Batch batch = batchMapper.toBatch(restockRequest);
        batch.setClinic(clinic);
        batch.setProduct(product);
        batch.setSupplier(supplier);

        Batch savedBatch = batchRepository.save(batch);

        InventoryTransaction inventoryTransaction = new InventoryTransaction();
        inventoryTransaction.setClinic(clinic);
        inventoryTransaction.setProduct(product);
        inventoryTransaction.setBatch(savedBatch);

        User userStub = new User();
        userStub.setId(userId);
        inventoryTransaction.setUser(userStub);

        inventoryTransaction.setTransactionType("Restock");
        inventoryTransaction.setQuantityChange(restockRequest.getQuantity());
        inventoryTransaction.setReason("Purchasing from a supplier " + supplier.getName());

        inventoryTransactionRepository.save(inventoryTransaction);

        System.out.println("Restock success: Batch id: " + savedBatch.getId());
    }

    @Transactional
    public void useProduct(UUID clinicId, UUID userId, UsageRequest usageRequest){
        Integer totalStock = batchRepository.getTotalQuantity(usageRequest.getProductId(), clinicId);
        if(totalStock == null || totalStock < usageRequest.getQuantity()){
            throw new IllegalArgumentException("Not enough in stock. Available: " + (totalStock == null ? 0 : totalStock));
        }

        List<Batch> batchList = batchRepository.findByProductIdAndClinicIdAndCurrentQuantityGreaterThanOrderByExpiryDateAsc(
                usageRequest.getProductId(), clinicId, 0
        );

        int remainingToDeduct = usageRequest.getQuantity();

        User user = new User();
        user.setId(userId);

        for(Batch batch : batchList){
            if(remainingToDeduct <= 0)
                break;

            int availableInBatch = batch.getCurrentQuantity();
            int deductFromBatch;

            if(availableInBatch >= remainingToDeduct){
                deductFromBatch = remainingToDeduct;
                remainingToDeduct = 0;
            } else {
                deductFromBatch = availableInBatch;
                remainingToDeduct -= availableInBatch;
            }

            batch.setCurrentQuantity(batch.getCurrentQuantity() - deductFromBatch);
            batchRepository.save(batch);

            InventoryTransaction transaction = new InventoryTransaction();
            transaction.setClinic(batch.getClinic());
            transaction.setProduct(batch.getProduct());
            transaction.setBatch(batch);
            transaction.setUser(user);
            transaction.setTransactionType("Usage");
            transaction.setQuantityChange(-deductFromBatch);
            transaction.setReason(usageRequest.getReason());

            inventoryTransactionRepository.save(transaction);
        }
        System.out.println("Usage success");

    }

}
