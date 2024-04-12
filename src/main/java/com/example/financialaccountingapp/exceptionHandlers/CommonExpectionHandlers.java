package com.example.financialaccountingapp.exceptionHandlers;

import com.example.financialaccountingapp.exceptions.FinAppApiException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class CommonExpectionHandlers {

    @ExceptionHandler(FinAppApiException.class)
    public ResponseEntity<Void> handleRuntimeException(final FinAppApiException exception){
        log.debug("There is some unexpected exception " + exception.getMessage());
        if (exception.getMessage() != null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Message", exception.getMessage()).build();

        } else {
            return ResponseEntity.ok().build();
        }
    }
}
