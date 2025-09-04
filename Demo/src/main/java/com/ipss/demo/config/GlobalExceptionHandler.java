package com.ipss.demo.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.CONFLICT) // 409
  public Map<String, Object> handleDataIntegrity(DataIntegrityViolationException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("status", 409);
    body.put("error", ex.getMessage() != null ? ex.getMessage() : "Violación de integridad de datos");
    return body;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
  public Map<String, Object> handleValidation(MethodArgumentNotValidException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("status", 400);
    Map<String, String> errors = new LinkedHashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
    body.put("errors", errors);
    return body;
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
  public Map<String, Object> handleNotReadable(HttpMessageNotReadableException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("status", 400);
    body.put("error", "Body inválido: " + (ex.getMostSpecificCause()!=null ? ex.getMostSpecificCause().getMessage() : ex.getMessage()));
    return body;
  }

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND) // 404
  public Map<String, Object> handleNotFound(NoSuchElementException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("status", 404);
    body.put("error", ex.getMessage()!=null ? ex.getMessage() : "No encontrado");
    return body;
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
  public Map<String, Object> handleGeneric(Exception ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("status", 500);
    body.put("error", "Error interno");
    return body;
  }
}
