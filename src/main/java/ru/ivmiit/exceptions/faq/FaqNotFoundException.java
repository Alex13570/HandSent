package ru.ivmiit.exceptions.faq;

import org.springframework.http.HttpStatus;
import ru.ivmiit.exceptions.HandSentException;

public class FaqNotFoundException extends HandSentException {

    public FaqNotFoundException() {
        super("answer and question not found", HttpStatus.NOT_FOUND);
    }
}
