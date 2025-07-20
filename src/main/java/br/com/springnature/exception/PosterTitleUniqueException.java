package br.com.springnature.exception;

public class PosterTitleUniqueException extends RuntimeException {
    public PosterTitleUniqueException(String message) {
        super(message);
    }
}
