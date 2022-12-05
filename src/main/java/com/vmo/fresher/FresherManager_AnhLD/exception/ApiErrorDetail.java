package com.vmo.fresher.FresherManager_AnhLD.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorDetail {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    protected final LocalDateTime timestamp = LocalDateTime.now();

    protected String message;

    protected String details;

    protected HttpStatus httpStatus;

    protected String entityName;

    protected String fieldName;

    protected Object fieldValue;
}
