package com.example.Ejercicio_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Ejercicio3Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Ejercicio3Application.class, args);
		LibrosRepository repositorio = context.getBean(LibrosRepository.class);

		Libros libro1 = new Libros(null, "La caida de los gigantes", "Ken Follet", 985);
		Libros libro2 = new Libros(null, "La comunidad del anillo", "tolkien", 850);
		repositorio.save(libro1);
		repositorio.save(libro2);

		System.out.println(repositorio.findAll());

	}

}
