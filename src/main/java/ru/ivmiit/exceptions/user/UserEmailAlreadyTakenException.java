package ru.ivmiit.exceptions.user;

import org.springframework.http.HttpStatus;
import ru.ivmiit.exceptions.HandSentException;

public class UserEmailAlreadyTakenException extends HandSentException {
    public UserEmailAlreadyTakenException() {
        super("Email already taken!", HttpStatus.BAD_REQUEST);
    }
}
