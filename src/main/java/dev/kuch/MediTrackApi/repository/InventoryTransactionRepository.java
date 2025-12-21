package dev.kuch.MediTrackApi.repository;

import dev.kuch.MediTrackApi.entity.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Artur Korol
 */

@Repository
public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, UUID> {

    List<InventoryTransaction> findAllByClinicIdOrderByCreatedAtDesc(UUID clinicId);

    List<InventoryTransaction> findAllByClinicIdAndProductIdOrderByCreatedAtDesc(UUID clinicId, UUID productId);

}
