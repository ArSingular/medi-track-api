package dev.kuch.MediTrackApi.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * @author Korol Artur
 * 19.12.2025
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomError> handleNotFoundException(EntityNotFoundException e){
        return new ResponseEntity<>(
                new CustomError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    public ResponseEntity<CustomError> handleBadRequest(IllegalArgumentException e){
        return new ResponseEntity<>(
                new CustomError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                new CustomError(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
    }

}
