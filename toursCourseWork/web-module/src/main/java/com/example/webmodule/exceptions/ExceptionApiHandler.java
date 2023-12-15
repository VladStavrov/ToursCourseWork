package com.example.webmodule.exceptions;
import com.example.commonmodule.exception.AppError;
import com.example.commonmodule.exception.LocalException;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(LocalException.class)
    public ResponseEntity<?> notFoundException(LocalException exception) {
        return  new ResponseEntity<>(new AppError(exception.getBody().getStatus(),exception.getReason()), exception.getStatusCode());
    }
    @ExceptionHandler(NonUniqueResultException.class)
    public ResponseEntity<?> notFoundException(NonUniqueResultException exception) {
        return  new ResponseEntity<>(new AppError( HttpStatus.NOT_FOUND.value(),"Ошибка в Базе данных,Таких значений несколько"), HttpStatus.NOT_FOUND);
    }

}