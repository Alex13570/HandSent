package ru.ivmiit.exceptions.product;

import org.springframework.http.HttpStatus;
import ru.ivmiit.exceptions.HandSentException;

public class ProductNotFoundException extends HandSentException {

    public ProductNotFoundException() {
        super("Product with this id does not exist!", HttpStatus.NOT_FOUND);
    }
}
