package com.vmo.fresher.FresherManager_AnhLD.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiErrorDetail> handleAllExceptions(Exception e, WebRequest request){
        ApiErrorDetail errorDetail = ApiErrorDetail.builder()
                .message(e.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AbstractCustomException.class)
    public final ResponseEntity<ApiErrorDetail> handleCustomExceptions(AbstractCustomException ace, WebRequest request){
        ace.getApiErrorDetail().setDetails(request.getDescription(false));
        return new ResponseEntity<>(ace.getApiErrorDetail(), ace.getApiErrorDetail().getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiErrorDetail errorDetails = ApiErrorDetail.builder()
                .message("Total Errors: " + ex.getErrorCount() + ". First Error: " + ex.getFieldError().getDefaultMessage())
                .details(request.getDescription(false))
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
