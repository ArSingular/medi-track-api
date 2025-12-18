package dev.kuch.MediTrackApi.repository;

import dev.kuch.MediTrackApi.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Artur Korol
 */

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, UUID> {

    boolean existsByOwnerEmail(String ownerEmail);

}
