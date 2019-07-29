package org.generation.brazil.backend.postagem;


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
@CrossOrigin(origins = "http://localhost:4200")
public class PostagemController {

    private static final String NOT_FOUND = "Não foi encontrado uma postagem com o id: ";

    @Autowired
    private PostagemRepository postagemRepository;

    @GetMapping("/postagem")
    public List<Postagem> getPostagem() {
        return postagemRepository.findAll();
    }

    @GetMapping("/postagem/{id}")
    public ResponseEntity<Postagem> getPostagem(@PathVariable(value = "id") Long postagemId)
            throws ResourceNotFoundException {
        Postagem postagem = postagemRepository.findById(postagemId)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + postagemId));
        return ResponseEntity.ok().body(postagem);
    }

    @PostMapping("/postagem")
    public Postagem createPostagem(@Valid @RequestBody Postagem postagem) {
        return postagemRepository.save(postagem);
    }
    @PutMapping("/postagem/{id}")
    public ResponseEntity<Postagem> updatePostagem(@PathVariable(value = "id") Long postagemId,
                                                 @Valid @RequestBody Postagem postagemDetails) throws ResourceNotFoundException {
        Postagem postagem = postagemRepository.findById(postagemId)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + postagemId));
        postagem.setTp_publicacao(postagemDetails.getTp_publicacao());
        postagem.setId_usuario_publicacao(postagemDetails.getId_usuario_publicacao());
        postagem.setPublicacao(postagemDetails.getPublicacao());
        postagem.setId_usuario_comentario(postagemDetails.getId_usuario_comentario());
        postagem.setComentario(postagemDetails.getComentario());
        final Postagem updatedPostagem = postagemRepository.save(postagem);
        return ResponseEntity.ok(updatedPostagem);
    }

    @DeleteMapping("/postagem/{id}")
    public Map<String, Boolean> deletePostagem(@PathVariable(value = "id") Long postagemId)
            throws ResourceNotFoundException {
        Postagem postagem = postagemRepository.findById(postagemId)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + postagemId));
        postagemRepository.delete(postagem);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
