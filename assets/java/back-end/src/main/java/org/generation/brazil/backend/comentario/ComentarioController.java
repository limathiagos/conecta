package org.generation.brazil.backend.comentario;

import org.generation.brazil.backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ComentarioController {

    private static final String NOT_FOUND = "Não foi encontrado um comentário com o id: ";
    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping("/comentarios")
    public List<Comentario> getAllComentarios() {
        return comentarioRepository.findAll();
    }

    @GetMapping("/comentarios/{id}")
    public ResponseEntity<Comentario> getComentariosById(@PathVariable(value = "id") Long comentarioId) {
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + comentarioId));
        return ResponseEntity.ok().body(comentario);}

    @PostMapping("/comentarios")
    public Comentario createComentario(@Valid @RequestBody Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @PutMapping("/comentarios/{id}")
    public ResponseEntity<Comentario> updateComentario(@PathVariable(value = "id") Long comentarioId,
                                                       @Valid @RequestBody Comentario comentarioDetails) {
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + comentarioId));

        comentario.setConteudoComentario(comentarioDetails.getConteudoComentario());

        final Comentario updateComentario = comentarioRepository.save(comentario);
        return ResponseEntity.ok(updateComentario);
    }

    @DeleteMapping("/comentarios/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long comentarioId) {
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + comentarioId));
        comentarioRepository.delete(comentario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
