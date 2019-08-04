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
public class PostagemController {

    private static final String NOT_FOUND = "Não foi encontrado uma postagem com o id: ";

    @Autowired
    private PostagemRepository postagemRepository;

    @GetMapping("/postagens")
    public List<Postagem> getAllPostagens() {
        return postagemRepository.findAll();
    }
    @GetMapping("/postagens/{id}")
    public ResponseEntity<Postagem> getPostagensById(@PathVariable(value = "id") Long postagemId) {
        Postagem postagem = postagemRepository.findById(postagemId)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + postagemId));
        return ResponseEntity.ok().body(postagem);}


    @PostMapping("/postagens")
    public Postagem createPostagem(@Valid @RequestBody Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    @PutMapping("/postagens/{id}")
    public ResponseEntity<Postagem> updatePostagem(@PathVariable(value = "id") Long postagemId,
                                                   @Valid @RequestBody Postagem postagemDetails) {
        Postagem postagem = postagemRepository.findById(postagemId)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + postagemId));
        postagem.setConteudo_postagem(postagemDetails.getConteudo_postagem());


        final Postagem updatePostagem = postagemRepository.save(postagem);
        return ResponseEntity.ok(updatePostagem);
    }

    @DeleteMapping("/postagens/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long postagemId) {
        Postagem postagem = postagemRepository.findById(postagemId)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + postagemId));

        postagemRepository.delete(postagem);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
