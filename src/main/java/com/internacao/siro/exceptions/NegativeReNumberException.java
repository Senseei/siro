package com.internacao.siro.exceptions;

public class NegativeReNumberException extends RuntimeException {
    public NegativeReNumberException() {
        super("Employee's RE number cannot be negative");
    }
}
