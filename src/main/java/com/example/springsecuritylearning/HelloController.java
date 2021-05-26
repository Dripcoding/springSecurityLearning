package com.example.springsecuritylearning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // accessible by all users
    @GetMapping("/")
    public String helloWorld() {
       return "hello world";
    }

    // accessible by only users with USER role
    @GetMapping("/user")
    public String user() { return "Welcome User"; }

    // accessible by only useres with ADMIN role
    @GetMapping("/admin")
    public String admin() { return "Welcome Admin"; }
}
