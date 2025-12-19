package dev.kuch.MediTrackApi.exception;

import lombok.Data;

import java.util.Date;

/**
 * @author Korol Artur
 * 19.12.2025
 */

@Data
public class CustomError {

    private int status;
    private String message;
    private Date timestamp;

    public CustomError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
