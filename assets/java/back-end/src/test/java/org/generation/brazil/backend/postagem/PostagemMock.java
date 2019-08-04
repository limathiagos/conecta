package org.generation.brazil.backend.postagem;

import com.github.javafaker.Faker;

import java.util.Locale;

public class PostagemMock {

    public static Postagem getPostagemMock() {
        Postagem postagem = new Postagem();
        Faker faker = new Faker(new Locale("pt-BR"));
        postagem.setConteudo_postagem(faker.name().firstName());
        return postagem;
    }
}
