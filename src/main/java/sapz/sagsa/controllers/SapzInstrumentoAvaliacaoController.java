package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapz.sagsa.models.dtos.SapzInstrumentoAvaliacaoDTO;
import sapz.sagsa.services.SapzInstrumentoAvaliacaoService;

import java.util.List;

@RestController
@RequestMapping("/api/sapz-instrumentos")
@RequiredArgsConstructor
public class SapzInstrumentoAvaliacaoController {

    private final SapzInstrumentoAvaliacaoService instrumentoService;

    @GetMapping("/situacao/{saId}")
    public ResponseEntity<List<SapzInstrumentoAvaliacaoDTO>> listarPorSituacaoAprendizagem(@PathVariable Long saId) {
        return ResponseEntity.ok(instrumentoService.listarPorSituacaoAprendizagemId(saId));
    }

    @PostMapping
    public ResponseEntity<SapzInstrumentoAvaliacaoDTO> criar(@Valid @RequestBody SapzInstrumentoAvaliacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(instrumentoService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SapzInstrumentoAvaliacaoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody SapzInstrumentoAvaliacaoDTO dto) {
        return ResponseEntity.ok(instrumentoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        instrumentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}