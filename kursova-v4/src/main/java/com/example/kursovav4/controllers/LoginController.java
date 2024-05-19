package com.example.kursovav4.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    @Operation(summary = "Отримати сторінку входу", description = "Отримати сторінку для авторизації користувача")
    public String getLoginPage() {

        return "login";
    }
}
