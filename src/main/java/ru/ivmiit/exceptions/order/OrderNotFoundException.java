package ru.ivmiit.exceptions.order;

import org.springframework.http.HttpStatus;
import ru.ivmiit.exceptions.HandSentException;

public class OrderNotFoundException extends HandSentException {

    public OrderNotFoundException() {
        super("Order not found", HttpStatus.NOT_FOUND);
    }
}
