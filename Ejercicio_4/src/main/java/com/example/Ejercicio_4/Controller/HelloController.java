package com.example.Ejercicio_4.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //@Value("${app.message}")
    //String message;

    @GetMapping("/Hello")
    public String saludo(){
        //System.out.println(message);
        return "Hola desde HelloController";
    }
}
