package com.foft.microserviceadmin.exception;

 import org.springframework.web.bind.annotation.ResponseStatus;
 import org.springframework.http.HttpStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AdminNotFoundException extends RuntimeException {
  public AdminNotFoundException(String message) {
      super(message);
  }
}