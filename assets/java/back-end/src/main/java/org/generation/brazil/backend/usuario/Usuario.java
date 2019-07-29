package org.generation.brazil.backend.usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.generation.brazil.backend.postagem.Postagem;

import java.util.Set;

@Data
@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String nome;

  @NotNull
  private String sobrenome;

  @NotNull
  private String email;

  @NotNull
  private String senha;

  @ManyToOne
  @JoinColumn(name="id_usuario_comentario")
  private Postagem postagem;

}
