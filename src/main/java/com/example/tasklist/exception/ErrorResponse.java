package com.example.tasklist.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

  private String source;

  private String reason;

  public static ErrorResponse create(String source) {
    return new ErrorResponse(source, null);
  }

  public static ErrorResponse create(String source, String reason) {
    return new ErrorResponse(source, reason);
  }
}
