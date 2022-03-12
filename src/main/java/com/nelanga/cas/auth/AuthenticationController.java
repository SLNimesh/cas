package com.nelanga.cas.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from AuthControl";
    }
}
