package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapz.sagsa.models.dtos.ModuloDTO;
import sapz.sagsa.services.ModuloService;

import java.util.List;

@RestController
@RequestMapping("/api/modulos")
@RequiredArgsConstructor
public class ModuloController {

    private final ModuloService moduloService;

    @GetMapping("/plano/{planoId}")
    public ResponseEntity<List<ModuloDTO>> listarPorPlanoCurso(@PathVariable Long planoId) {
        return ResponseEntity.ok(moduloService.listarPorPlanoCursoId(planoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuloDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(moduloService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ModuloDTO> criar(@Valid @RequestBody ModuloDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(moduloService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuloDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ModuloDTO dto) {
        return ResponseEntity.ok(moduloService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        moduloService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}