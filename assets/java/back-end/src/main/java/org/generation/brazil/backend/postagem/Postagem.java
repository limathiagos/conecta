package org.generation.brazil.backend.postagem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.generation.brazil.backend.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String tp_publicacao;

    @NotNull
    private Long id_usuario_publicacao;

    @NotNull
    private String publicacao;

    @NotNull
    private Usuario id_usuario_comentario;

    @OneToMany(mappedBy = "comentario", targetEntity = Usuario.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Usuario> usuarios;
}
