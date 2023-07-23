package com.dev.gam.mswebfluxjpa.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(CustomizedMessageException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(CustomizedMessageException ex) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());

    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setTimestamp(LocalDateTime.now());
    errorResponse.setDetails(details);

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  /*@ExceptionHandler(DataAccessException.class)
  public ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setTimestamp(LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }*/

  @ExceptionHandler(WebExchangeBindException.class)
  public ResponseEntity<ErrorResponse> handleValidationErrors(WebExchangeBindException ex) {

    // Obtener los errores de validación
    BindingResult bindingResult = ex.getBindingResult();

    List<String> errors = bindingResult.getFieldErrors()
        .stream()
        .map((e) -> e.getField() + " " + e.getDefaultMessage())
        .collect(Collectors.toList());

    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    errorResponse.setMessage("Validation failed. Error count: " + bindingResult.getErrorCount());
    errorResponse.setTimestamp(LocalDateTime.now());
    errorResponse.setDetails(errors);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

}

