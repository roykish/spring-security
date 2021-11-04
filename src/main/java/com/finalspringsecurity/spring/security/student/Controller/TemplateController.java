package com.finalspringsecurity.spring.security.student.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TemplateController {
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
}
