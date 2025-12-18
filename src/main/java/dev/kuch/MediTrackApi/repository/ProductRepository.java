package dev.kuch.MediTrackApi.repository;

import dev.kuch.MediTrackApi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Artur Korol
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findAllByClinicId(UUID clinicId);

    Optional<Product> findByIdAndClinicId(UUID Id, UUID clinicId);

    boolean existsByNameAndClinicId(String name, UUID clinicId);
}
