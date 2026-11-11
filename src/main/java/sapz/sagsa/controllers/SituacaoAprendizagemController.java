package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapz.sagsa.models.dtos.SituacaoAprendizagemDTO;
import sapz.sagsa.services.SituacaoAprendizagemService;

import java.util.List;

@RestController
@RequestMapping("/api/situacoes-aprendizagem")
@RequiredArgsConstructor
public class SituacaoAprendizagemController {

    private final SituacaoAprendizagemService saService;

    @GetMapping
    public ResponseEntity<List<SituacaoAprendizagemDTO>> listarTodas() {
        return ResponseEntity.ok(saService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SituacaoAprendizagemDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(saService.buscarPorId(id));
    }

    @GetMapping("/instrutor/{instrutorId}")
    public ResponseEntity<List<SituacaoAprendizagemDTO>> listarPorInstrutor(@PathVariable Long instrutorId) {
        return ResponseEntity.ok(saService.listarPorInstrutorId(instrutorId));
    }

    @PostMapping
    public ResponseEntity<SituacaoAprendizagemDTO> criar(@Valid @RequestBody SituacaoAprendizagemDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(saService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SituacaoAprendizagemDTO> atualizar(@PathVariable Long id, @Valid @RequestBody SituacaoAprendizagemDTO dto) {
        return ResponseEntity.ok(saService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        saService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}