package br.com.springnature.handler;

import br.com.springnature.exception.ApiError;
import br.com.springnature.exception.PosterNotFoundException;
import br.com.springnature.exception.PosterTitleUniqueException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(PosterNotFoundException.class)
    public ResponseEntity<ApiError> posterNotFoundException(PosterNotFoundException ex){
        ApiError error = ApiError
                .builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .timestamp(LocalDateTime.now())
                .messages(List.of(ex.getMessage()))
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    // DataIntegrityViolationException
    @ExceptionHandler(PosterTitleUniqueException.class)
    public ResponseEntity<ApiError> posterTitleUniqueException(PosterTitleUniqueException ex){
        ApiError error = ApiError
                .builder()
                .code(HttpStatus.CONFLICT.value())
                .status(HttpStatus.CONFLICT.name())
                .timestamp(LocalDateTime.now())
                .messages(List.of(ex.getMessage()))
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    // MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> argumentNotValidException(MethodArgumentNotValidException ex, BindingResult result){

        ApiError error = ApiError
                .builder()
                .code(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.name())
                .timestamp(LocalDateTime.now())
                .errors(addErrors(result))
                .build();

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(error);
    }

    private Map<String, String> addErrors(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        for(FieldError error : result.getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return errors;
    }

}
