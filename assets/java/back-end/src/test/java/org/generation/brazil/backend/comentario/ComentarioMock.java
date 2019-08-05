package org.generation.brazil.backend.comentario;

import com.github.javafaker.Faker;
import java.util.Locale;

public class ComentarioMock {

    public static Comentario getComentarioMock() {
        Comentario comentario = new Comentario();
        Faker faker = new Faker(new Locale("pt-BR"));
        comentario.setConteudoComentario(faker.name().firstName());
        return comentario;
    }
}
