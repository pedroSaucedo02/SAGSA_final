package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapz.sagsa.models.dtos.PlanoCursoDTO;
import sapz.sagsa.services.PlanoCursoService;

import java.util.List;

@RestController
@RequestMapping("/api/planos-curso")
@RequiredArgsConstructor
public class PlanoCursoController {

    private final PlanoCursoService planoCursoService;

    @GetMapping
    public ResponseEntity<List<PlanoCursoDTO>> listarTodos() {
        return ResponseEntity.ok(planoCursoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoCursoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(planoCursoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PlanoCursoDTO> criar(@Valid @RequestBody PlanoCursoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planoCursoService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoCursoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PlanoCursoDTO dto) {
        return ResponseEntity.ok(planoCursoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        planoCursoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}