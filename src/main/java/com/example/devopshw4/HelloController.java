package com.example.devopshw4;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello from Demo Spring Boot on K8s, blbalba";
    }
}
