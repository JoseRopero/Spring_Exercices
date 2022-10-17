package com.example.Ejercicio_4.Controller;

import com.example.Ejercicio_4.Entities.Laptop;
import com.example.Ejercicio_4.Repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    private LaptopRepository repository;

    public LaptopController(LaptopRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/laptop")
    public List<Laptop> findAll(){
        return repository.findAll();
    }
    @GetMapping("/api/laptop/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable long id){

        Optional<Laptop> laptopOpt = repository.findById(id);
        if (laptopOpt.isPresent()){  //Si el laptop existe...
            return ResponseEntity.ok(laptopOpt.get());  //Esto nos devuelve el laptop.
        }
        else{
            return ResponseEntity.notFound().build();  //Esto nos devuelve un 404.
        }
    }

    @PostMapping("/api/laptop")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){
        //Guardar un libro recibido por parámetros en la BD.
        if (laptop.getId() != null){  //Si al crear el laptop existe el id, entonces mandaríamos un error.
            log.warn("Laptop already exists");  //Para guardar un registro de los errores que puedan surgir.
            return ResponseEntity.badRequest().build();  //Mandaríamos el mensaje de badRequest.
        }
        Laptop result = repository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/api/laptop")  //El método put lo utilizaremos para actualizar algún laptop de BD
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if(laptop.getId() == null){  //Si el laptop no tiene id entonces estamos creando y no sería este método.
            log.warn("Estas intentando actualizar un laptop que no existe");
            return ResponseEntity.badRequest().build();  //Mandaríamos el mensaje de badRequest.
        }
        if(!repository.existsById(laptop.getId())){  //Si no existe el id...
            log.warn("El laptop no existe");
            return ResponseEntity.notFound().build();  //Un 404.
        }
        //Proceso de actualización
        Laptop result = repository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/api/laptop/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        if(!repository.existsById(id)){  //Si no existe el id...
            log.warn("El laptop que quiere borrar no existe");
            return ResponseEntity.notFound().build();  //Un 404.
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/laptop")
    public ResponseEntity<Laptop> deleteAll(){
        if(repository.count() == 0){
            log.warn("No hay laptop en BD");
        }
        log.info("Borrando todos los laptop");
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
