package com.met.jumbo.exception;

import com.met.jumbo.dto.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(WeatherNotFoundException.class)
    public ResponseEntity handleCPExceptions(WeatherNotFoundException ex){
        return new ResponseEntity(new Error("JW-1001", List.of("Weather not found")), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> processUnmergeException(final ConstraintViolationException ex) {
        List errors = ex.getConstraintViolations().stream().map(v -> v.getMessage()).collect(Collectors.toList());

        return new ResponseEntity(new Error("JW-1002", errors), HttpStatus.BAD_REQUEST);
    }
}
