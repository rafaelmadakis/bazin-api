package com.bazin.exception;

public class UnauthorizedException extends RuntimeException {

  public UnauthorizedException(String msg) {
    super(msg);
  }
}

