package com.example.todo_app.home;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class HomeController {
    @GetMapping("/")
    
    public String home() {
        return "Hello 👋🏾";
    }
}
