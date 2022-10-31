package ru.ivmiit.exceptions.news;

import org.springframework.http.HttpStatus;
import ru.ivmiit.exceptions.HandSentException;

public class NewsNotFoundException extends HandSentException {

    public NewsNotFoundException() {
        super("news not found", HttpStatus.NOT_FOUND);
    }

}
