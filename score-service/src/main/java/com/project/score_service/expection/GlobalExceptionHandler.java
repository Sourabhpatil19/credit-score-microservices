package com.project.score_service.expection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    public ResponseEntity<String> handleRuntimeException(
            RuntimeException ex) {

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
    @ExceptionHandler(CreditScoreNotFound.class)
    public ResponseEntity<String> handleNotFoundException(
            RuntimeException ex) {

        return ResponseEntity
                .notFound().build();
    }
}
