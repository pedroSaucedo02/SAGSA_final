package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapz.sagsa.models.dtos.SapzCompartilhamentoDTO;
import sapz.sagsa.services.SapzCompartilhamentoService;

import java.util.List;

@RestController
@RequestMapping("/api/sapz-compartilhamentos")
@RequiredArgsConstructor
public class SapzCompartilhamentoController {

    private final SapzCompartilhamentoService compartilhamentoService;

    @GetMapping("/situacao/{saId}")
    public ResponseEntity<List<SapzCompartilhamentoDTO>> listarPorSituacaoAprendizagem(@PathVariable Long saId) {
        return ResponseEntity.ok(compartilhamentoService.listarPorSituacaoAprendizagemId(saId));
    }

    @PostMapping
    public ResponseEntity<SapzCompartilhamentoDTO> compartilhar(@Valid @RequestBody SapzCompartilhamentoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(compartilhamentoService.compartilhar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCompartilhamento(@PathVariable Long id) {
        compartilhamentoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}