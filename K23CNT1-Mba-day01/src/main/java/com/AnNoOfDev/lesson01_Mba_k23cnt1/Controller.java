package com.AnNoOfDev.lesson01_Mba_k23cnt1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String sayHello() {
        return "✅ Spring Boot is running successfully!";
    }
}
