package dev.kuch.MediTrackApi.repository;

import dev.kuch.MediTrackApi.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Artur Korol
 */

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
}
