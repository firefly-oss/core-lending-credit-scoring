/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.scoring.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler for validation errors in the Credit Scoring application.
 * This handler provides consistent error responses for validation failures.
 */
@RestControllerAdvice
@Slf4j
public class ValidationExceptionHandler {

    /**
     * Handles validation errors from @Valid annotation on request bodies.
     * This occurs when validation annotations on DTO fields fail.
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ValidationErrorResponse>> handleValidationErrors(WebExchangeBindException ex) {
        log.warn("Validation error occurred: {}", ex.getMessage());
        
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        });

        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Request validation failed")
                .fieldErrors(fieldErrors)
                .build();

        return Mono.just(ResponseEntity.badRequest().body(errorResponse));
    }

    /**
     * Handles constraint violation exceptions from method-level validation.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Mono<ResponseEntity<ValidationErrorResponse>> handleConstraintViolationException(ConstraintViolationException ex) {
        log.warn("Constraint violation occurred: {}", ex.getMessage());
        
        Map<String, String> fieldErrors = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));

        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Constraint Violation")
                .message("Request validation failed")
                .fieldErrors(fieldErrors)
                .build();

        return Mono.just(ResponseEntity.badRequest().body(errorResponse));
    }

    /**
     * Response structure for validation errors.
     */
    public static class ValidationErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private Map<String, String> fieldErrors;

        public static ValidationErrorResponseBuilder builder() {
            return new ValidationErrorResponseBuilder();
        }

        // Getters
        public LocalDateTime getTimestamp() { return timestamp; }
        public int getStatus() { return status; }
        public String getError() { return error; }
        public String getMessage() { return message; }
        public Map<String, String> getFieldErrors() { return fieldErrors; }

        // Setters
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        public void setStatus(int status) { this.status = status; }
        public void setError(String error) { this.error = error; }
        public void setMessage(String message) { this.message = message; }
        public void setFieldErrors(Map<String, String> fieldErrors) { this.fieldErrors = fieldErrors; }

        public static class ValidationErrorResponseBuilder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private Map<String, String> fieldErrors;

            public ValidationErrorResponseBuilder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public ValidationErrorResponseBuilder status(int status) {
                this.status = status;
                return this;
            }

            public ValidationErrorResponseBuilder error(String error) {
                this.error = error;
                return this;
            }

            public ValidationErrorResponseBuilder message(String message) {
                this.message = message;
                return this;
            }

            public ValidationErrorResponseBuilder fieldErrors(Map<String, String> fieldErrors) {
                this.fieldErrors = fieldErrors;
                return this;
            }

            public ValidationErrorResponse build() {
                ValidationErrorResponse response = new ValidationErrorResponse();
                response.setTimestamp(this.timestamp);
                response.setStatus(this.status);
                response.setError(this.error);
                response.setMessage(this.message);
                response.setFieldErrors(this.fieldErrors);
                return response;
            }
        }
    }
}
