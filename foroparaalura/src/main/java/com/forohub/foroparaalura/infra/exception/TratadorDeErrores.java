package com.forohub.foroparaalura.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> tratarErrorRuntime(RuntimeException ex) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse(ex.getMessage())
        );
    }

    public record ErrorResponse(String error) {}
}
