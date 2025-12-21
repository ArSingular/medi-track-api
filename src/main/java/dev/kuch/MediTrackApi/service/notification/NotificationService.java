package dev.kuch.MediTrackApi.service.notification;

import dev.kuch.MediTrackApi.event.LowStockEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Korol Artur
 * 21.12.2025
 */


@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender mailSender;

    @Async
    @EventListener
    public void handleLowStockEvent(LowStockEvent event){
        sendEmail(event);
    }

    private void  sendEmail(LowStockEvent event){
        try{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getEmail());
        message.setSubject("ALARM: " + event.getProductName());
        message.setText(String.format(
                "Clinic: %s\nProduct: %s\nRemaining: %d .\nThreshold: %d шт.\n\nMake an order urgently!",
                event.getClinicName(),
                event.getProductName(),
                event.getCurrentQuantity(),
                event.getThreshold()
        ));
        mailSender.send(message);
    }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
