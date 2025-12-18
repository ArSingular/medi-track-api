package dev.kuch.MediTrackApi.repository;

import dev.kuch.MediTrackApi.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Artur Korol
 */

@Repository
public interface BatchRepository extends JpaRepository<Batch, UUID> {

    List<Batch> findByProductIdAndClinicIdAndCurrentQuantityGreaterThanOrderByExpiryDateAsc(UUID productId, UUID clinicId, Integer currentQuantity);

    @Query("SELECT SUM(b.currentQuantity) FROM Batch b WHERE b.product.id = :productId AND b.clinic.id = :clinicId")
    Integer getTotalQuantity(@Param("productId") UUID productId, @Param("clinicId") UUID clinicId);

}
