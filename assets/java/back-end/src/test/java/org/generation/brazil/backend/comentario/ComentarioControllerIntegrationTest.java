package org.generation.brazil.backend.comentario;

import org.generation.brazil.backend.BackEndApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Collections;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackEndApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComentarioControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/v1/comentarios/";
    }

    private String token;

    @Before
    public void init() {
        this.token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNTY0NzY4ODU1LCJleHAiOjE1NjQ3Njk3NTV9.h_fZymLIMA9NeN6jLbY50dbOeQJAR2H8q0Diil67ZsSLdd69vSy9AEjSk78btwv6IDXo7nHvm8Y2srX26gDMvw";
    }

    @Test
    public void testaCriacaoDeUmNovoComentario() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + this.token);
        HttpEntity<Comentario> entity = new HttpEntity<>(ComentarioMock.getComentarioMock(), headers);
        ResponseEntity<Comentario> responseEntity = testRestTemplate.exchange(
                getRootUrl(),
                HttpMethod.POST,
                entity,
                Comentario.class
        );

        assertNotNull(responseEntity);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testaConsultaDeTodosComentarios() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(
                getRootUrl(),
                HttpMethod.GET,
                entity,
                String.class);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testaConsultaPorId() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Comentario> response = testRestTemplate.exchange(
                getRootUrl() + "1",
                HttpMethod.GET,
                entity,
                Comentario.class);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testaAtualizacaoDeUmComentario() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + this.token);

        HttpEntity<Comentario> entity = new HttpEntity<>(ComentarioMock.getComentarioMock(), headers);

        ResponseEntity<Comentario> responseEntity = testRestTemplate.exchange(
                getRootUrl() + "1",
                HttpMethod.PUT,
                entity,
                Comentario.class
        );

        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

}
