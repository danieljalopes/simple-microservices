package com.bueno.account.exception;

import com.bueno.account.dto.ResponseExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class AccountExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseExceptionDTO> handleCommonsException(Throwable e, WebRequest request) {
        ResponseExceptionDTO responseExceptionDTO =
                new ResponseExceptionDTO(
                        LocalDateTime.now(),
                        e.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity<>(responseExceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseExceptionDTO> handleNotFoundException(Throwable e, WebRequest request) {
        ResponseExceptionDTO responseExceptionDTO =
                new ResponseExceptionDTO(
                        LocalDateTime.now(),
                        e.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity<>(responseExceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ResourceUnprocessableException.class, ObjectOptimisticLockingFailureException.class})
    public ResponseEntity<ResponseExceptionDTO> handleResourceUnprocessableException(Throwable e, WebRequest request) {
        ResponseExceptionDTO responseExceptionDTO =
                new ResponseExceptionDTO(
                        LocalDateTime.now(),
                        e.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity<>(responseExceptionDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }


}