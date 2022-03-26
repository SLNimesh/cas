package com.nelanga.cas.auth;

import com.nelanga.cas.exception.types.AuthFailedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Test!";
    }

    @GetMapping("/exception")
    public void throwException() {
        throw new AuthFailedException();
    }
}
