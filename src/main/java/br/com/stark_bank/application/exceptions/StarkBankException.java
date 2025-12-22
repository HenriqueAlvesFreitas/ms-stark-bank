package br.com.stark_bank.application.exceptions;

public class StarkBankException extends RuntimeException {
    public StarkBankException(String message, Throwable cause) {
        super(message, cause);
    }
}
