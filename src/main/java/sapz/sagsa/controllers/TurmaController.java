package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapz.sagsa.models.dtos.TurmaDTO;
import sapz.sagsa.services.TurmaService;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<TurmaDTO>> listarTodas() {
        return ResponseEntity.ok(turmaService.listarTodas());
    }

    @GetMapping("/escola/{escolaId}")
    public ResponseEntity<List<TurmaDTO>> listarPorEscola(@PathVariable Long escolaId) {
        return ResponseEntity.ok(turmaService.listarPorEscolaId(escolaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(turmaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TurmaDTO> criar(@Valid @RequestBody TurmaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody TurmaDTO dto) {
        return ResponseEntity.ok(turmaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        turmaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}