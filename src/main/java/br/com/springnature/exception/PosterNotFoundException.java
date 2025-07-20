package br.com.springnature.exception;

public class PosterNotFoundException extends RuntimeException {
    public PosterNotFoundException(String message) {
        super(message);
    }
}
