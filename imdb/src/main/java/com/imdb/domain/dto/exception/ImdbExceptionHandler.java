package com.imdb.domain.dto.exception;

import com.imdb.domain.dto.ImdbResponseCode;
import com.imdb.domain.dto.ImdbResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class ImdbExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> illegalArgumentException(
            Exception ex) {
        String exceptionMessageDesc = ex.getLocalizedMessage();
        if (exceptionMessageDesc == null) exceptionMessageDesc = ex.toString();

        ImdbErrorMessage errorMessage = new ImdbErrorMessage(new Date(), exceptionMessageDesc, null);
        return ImdbResponseEntity.response(ImdbResponseCode.BAD_REQUEST.getCode(), ImdbResponseCode.BAD_REQUEST.getMessage(), HttpStatus.BAD_REQUEST, errorMessage);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> generalException(
            Exception ex) {
        String exceptionMessageDesc = ex.getLocalizedMessage();
        if (exceptionMessageDesc == null) exceptionMessageDesc = ex.toString();

        ImdbErrorMessage errorMessage = new ImdbErrorMessage(new Date(), exceptionMessageDesc, null);
        return ImdbResponseEntity.response(ImdbResponseCode.SYSTEM_ERROR.getCode(), ImdbResponseCode.SYSTEM_ERROR.getMessage(), HttpStatus.BAD_REQUEST, errorMessage);

    }

}
