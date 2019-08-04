package org.generation.brazil.backend.postagem;

import lombok.Data;
import org.generation.brazil.backend.user.User;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String conteudo_postagem;

    @ManyToOne
    @JoinColumn (name = "user_username")
    private User user;
}
