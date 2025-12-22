package dev.kuch.MediTrackApi.service;

import dev.kuch.MediTrackApi.event.LowStockEvent;
import org.springframework.stereotype.Service;

/**
 * @author Korol Artur
 * 22.12.2025
 */

@Service
public interface NotificationService {

    void handleLowStockEvent(LowStockEvent event);

}
