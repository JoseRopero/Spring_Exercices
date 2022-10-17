package com.example.Ejercicio_4.Controller;

import com.example.Ejercicio_4.Entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;  //Creamos este objeto para test de integración.

    @Autowired  //Para indicarle a Spring que nos inyecte este builder para usarlo con RestTemplate que se encargará de lanzar las peticiones HTML.
    private RestTemplateBuilder restTemplateBuilder;  //Este se encarga de construir el anterior.

    @LocalServerPort  //Inyectamos un puerto para el setUp.
    private int port;

    @BeforeEach  //ESto se va a iniciar antes del método.
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {

        ResponseEntity<Laptop[]> response  = testRestTemplate.getForEntity("/api/books", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Laptop> laptop = Arrays.asList(response.getBody());
        System.out.println(laptop.size());
    }

    @Test
    void findOneById() {

        ResponseEntity<Laptop> response  = testRestTemplate.getForEntity("/api/laptop/1", Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "marca": "Libro creado desde Spring Test",
                    "modelo": "Yuval Noah",
                    "memoria": 650,
                    "precio": 19.99,
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/books", HttpMethod.POST, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("Laptop creado desde Spring Test", result.getMarca());
    }
}