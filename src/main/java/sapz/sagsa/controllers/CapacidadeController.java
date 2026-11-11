package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapz.sagsa.models.dtos.CapacidadeDTO;
import sapz.sagsa.services.CapacidadeService;

import java.util.List;

@RestController
@RequestMapping("/api/capacidades")
@RequiredArgsConstructor
public class CapacidadeController {

    private final CapacidadeService capacidadeService;

    @GetMapping("/unidade-curricular/{ucId}")
    public ResponseEntity<List<CapacidadeDTO>> listarPorUnidadeCurricular(@PathVariable Long ucId) {
        return ResponseEntity.ok(capacidadeService.listarPorUnidadeCurricularId(ucId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CapacidadeDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(capacidadeService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CapacidadeDTO> criar(@Valid @RequestBody CapacidadeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(capacidadeService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CapacidadeDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CapacidadeDTO dto) {
        return ResponseEntity.ok(capacidadeService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        capacidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}