package org.example.creatures.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class GeneralExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not Found");
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<String> handleException(NotAuthorizedException ex) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Vous ne passerez PAS !!!!");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Map<String,String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<Map<String,String>> errors = ex.getFieldErrors().stream().map(fieldError ->{
            Map<String,String> error = new HashMap<>();
            error.put(fieldError.getField(),fieldError.getDefaultMessage());
            return error;
        } ).toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
