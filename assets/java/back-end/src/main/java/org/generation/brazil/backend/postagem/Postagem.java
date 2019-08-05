package org.generation.brazil.backend.postagem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.generation.brazil.backend.comentario.Comentario;
import org.generation.brazil.backend.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column (name = "conteudoPostagem")
    private String conteudoPostagem;

    @JsonIgnore
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;


    @OneToMany (mappedBy = "postagem", cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();

}
