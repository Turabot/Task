package com.example.tasklist;

import com.example.tasklist.exception.ErrorResponse;
import com.example.tasklist.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class RestExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  protected ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ErrorResponse.create("Not Found", exception.getMessage()));
  }
}
