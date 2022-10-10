package com.example.Ejercicio_4.Controller;

import com.example.Ejercicio_4.Entities.Laptop;
import com.example.Ejercicio_4.Repository.LaptopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LaptopController {

    private LaptopRepository repository;

    public LaptopController(LaptopRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/laptop")
    public List<Laptop> listado(){
        return repository.findAll();
    }

    @PostMapping("/api/laptop")
    public Laptop crear(@RequestBody Laptop laptop){
        return repository.save(laptop);
    }
}
