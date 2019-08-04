package org.generation.brazil.backend.comentario;

import lombok.Data;
import org.generation.brazil.backend.user.User;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    private String conteudo_comentario;

    @ManyToOne
    @JoinColumn (name = "user_username")
    private User user;
}
