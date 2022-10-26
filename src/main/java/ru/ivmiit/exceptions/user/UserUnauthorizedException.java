package ru.ivmiit.exceptions.user;

import org.springframework.http.HttpStatus;
import ru.ivmiit.exceptions.HandSentException;

public class UserUnauthorizedException extends HandSentException {
    public UserUnauthorizedException(String title) {
        super(title, HttpStatus.UNAUTHORIZED);
    }
}
