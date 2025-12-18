package dev.kuch.MediTrackApi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Korol Artur
 * 12.12.2025
 */

@Entity
@Table(name = "clinics")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "owner_email", nullable = false)
    private String ownerEmail;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "paid_until")
    private LocalDate paidUntil;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

}
