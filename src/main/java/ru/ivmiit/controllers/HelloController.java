package ru.ivmiit.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(
        exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
public class HelloController {

    @GetMapping
    public String getHello() {
        return "<h1>Hello user</h1>";
    }
}
