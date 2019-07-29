package org.generation.brazil.backend.usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.generation.brazil.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

  private static final String NOT_FOUND = "Não foi encontrado um usuário com o id: ";

  @Autowired
  private UsuarioRepository usuarioRepository;

  @GetMapping("/usuario")
  public List<Usuario> getUsuarios() {
    return usuarioRepository.findAll();
  }

  @GetMapping("/usuario/{id}")
  public ResponseEntity<Usuario> getUsuarios(@PathVariable(value = "id") Long usuarioId)
      throws ResourceNotFoundException {
    Usuario usuario = usuarioRepository.findById(usuarioId)
        .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + usuarioId));
    return ResponseEntity.ok().body(usuario);
  }

  @PostMapping("/usuario")
  public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  @PutMapping("/usuario/{id}")
  public ResponseEntity<Usuario> updateUsuario(@PathVariable(value = "id") Long usuarioId,
                                               @Valid @RequestBody Usuario usuarioDetails) throws ResourceNotFoundException {
    Usuario usuario = usuarioRepository.findById(usuarioId)
        .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + usuarioId));
    usuario.setNome(usuarioDetails.getNome());
    usuario.setSobrenome(usuarioDetails.getSobrenome());
    usuario.setEmail(usuarioDetails.getEmail());
    usuario.setSenha(usuarioDetails.getSenha());
    final Usuario updatedUsuario = usuarioRepository.save(usuario);
    return ResponseEntity.ok(updatedUsuario);
  }

  @DeleteMapping("/usuario/{id}")
  public Map<String, Boolean> deleteUsuario(@PathVariable(value = "id") Long usuarioId)
      throws ResourceNotFoundException {
    Usuario usuario = usuarioRepository.findById(usuarioId)
        .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + usuarioId));
    usuarioRepository.delete(usuario);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }

}
