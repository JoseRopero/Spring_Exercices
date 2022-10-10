package com.example.Ejercicio_4;

import com.example.Ejercicio_4.Entities.Laptop;
import com.example.Ejercicio_4.Repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Ejercicio4Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Ejercicio4Application.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		Laptop laptop1 = new Laptop(null, "HP", "Ultimate", 512, 1545.99);
		Laptop laptop2 = new Laptop(null, "ASUS", "Gaming", 1000, 2400.99);

		repository.save(laptop1);
		repository.save(laptop2);


	}

}
