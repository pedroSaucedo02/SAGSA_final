package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sapz.sagsa.models.SituacaoAprendizagem;
import sapz.sagsa.services.SituacaoAprendizagemService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/situacoes-aprendizagem")
@Validated
public class SituacaoAprendizagemController {
    @Autowired
    private SituacaoAprendizagemService situacaoAprendizagemService;

    @GetMapping
    public ResponseEntity<List<SituacaoAprendizagem>> findAll() {
        return ResponseEntity.ok(situacaoAprendizagemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SituacaoAprendizagem> findById(@PathVariable Long id) {
        return ResponseEntity.ok(situacaoAprendizagemService.findById(id));
    }

    @GetMapping("/instrutor/{instrutorId}")
    public ResponseEntity<List<SituacaoAprendizagem>> findByInstrutor(@PathVariable Long instrutorId) {
        return ResponseEntity.ok(situacaoAprendizagemService.findByInstrutor(instrutorId));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody SituacaoAprendizagem situacaoAprendizagem) {
        situacaoAprendizagem.setId(null);
        SituacaoAprendizagem created = situacaoAprendizagemService.create(situacaoAprendizagem);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @Valid @RequestBody SituacaoAprendizagem situacaoAprendizagem,
            @PathVariable Long id) {
        situacaoAprendizagem.setId(id);
        situacaoAprendizagemService.update(id, situacaoAprendizagem);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        situacaoAprendizagemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
