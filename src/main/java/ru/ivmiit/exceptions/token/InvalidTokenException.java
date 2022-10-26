package ru.ivmiit.exceptions.token;

import org.springframework.http.HttpStatus;
import ru.ivmiit.exceptions.HandSentException;

public class InvalidTokenException extends HandSentException {
    public InvalidTokenException(String title) {
        super(title, HttpStatus.UNAUTHORIZED);
    }

    public InvalidTokenException() {
        this("Invalid token wtf");
    }
}
