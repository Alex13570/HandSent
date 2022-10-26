package ru.ivmiit.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class HandSentException extends RuntimeException {

    private final HttpStatus httpStatus;

    public HandSentException(String title, HttpStatus status) {
        super(title);
        httpStatus = status;
    }
}
