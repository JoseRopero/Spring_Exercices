package com.example.Ejercicio_4.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/Hello")
    public String saludo(){
        return "Hola desde HelloController";
    }
}
