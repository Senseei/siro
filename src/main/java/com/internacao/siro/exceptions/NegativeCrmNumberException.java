package com.internacao.siro.exceptions;

public class NegativeCrmNumberException extends RuntimeException {
    public NegativeCrmNumberException() {
        super("Doctor's CRM cannot be a negative number");
    }

    public NegativeCrmNumberException(String message) {
        super(message);
    }
}
