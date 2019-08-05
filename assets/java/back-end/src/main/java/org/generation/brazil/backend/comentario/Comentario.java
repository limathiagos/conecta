package org.generation.brazil.backend.comentario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.generation.brazil.backend.postagem.Postagem;
import org.generation.brazil.backend.user.User;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Setter
@Getter
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column (name = "conteudoComentario")
    private String conteudoComentario;


    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn (name= "postagem_id")
    private Postagem postagem;
}
