package com.cg.coffeemanagement.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppUtil {
    public ResponseEntity<?> mapErrorToResponse(BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        Map<String, String> error = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            error.put(fieldError.getField(), fieldError.getDefaultMessage());

        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
