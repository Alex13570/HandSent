package ru.ivmiit.exceptions.user;

import org.springframework.http.HttpStatus;
import ru.ivmiit.exceptions.HandSentException;

public class UserNotFoundException extends HandSentException {

    public UserNotFoundException() {
        super("User not found", HttpStatus.NOT_FOUND);
    }
}
