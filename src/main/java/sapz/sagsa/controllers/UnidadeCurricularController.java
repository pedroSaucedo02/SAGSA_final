package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapz.sagsa.models.dtos.UnidadeCurricularRequestDTO;
import sapz.sagsa.models.dtos.UnidadeCurricularResponseDTO;
import sapz.sagsa.services.UnidadeCurricularService;

import java.util.List;

@RestController
@RequestMapping("/api/unidades-curriculares")
@RequiredArgsConstructor
public class UnidadeCurricularController {

    private final UnidadeCurricularService ucService;

    @GetMapping
    public ResponseEntity<List<UnidadeCurricularResponseDTO>> listarTodas() {
        return ResponseEntity.ok(ucService.listarTodas());
    }

    @GetMapping("/modulo/{moduloId}")
    public ResponseEntity<List<UnidadeCurricularResponseDTO>> listarPorModulo(@PathVariable Long moduloId) {
        return ResponseEntity.ok(ucService.listarPorModuloId(moduloId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeCurricularResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ucService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<UnidadeCurricularResponseDTO> criar(@Valid @RequestBody UnidadeCurricularRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ucService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeCurricularResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody UnidadeCurricularRequestDTO dto) {
        return ResponseEntity.ok(ucService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ucService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}