package com.internacao.siro.exceptions;

public class NegativeMedicalRecordException extends IllegalArgumentException {
    public NegativeMedicalRecordException() {
        super("Medical record number cannot be negative.");
    }

    public NegativeMedicalRecordException(String message) {
        super(message);
    }
}
