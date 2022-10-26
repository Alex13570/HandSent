package ru.ivmiit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ivmiit.models.RefreshToken;
import ru.ivmiit.models.UserEntity;

@SpringBootApplication
public class HandSentApplication {

    public static void main(String[] args) {
        SpringApplication.run(HandSentApplication.class, args);
    }

}
