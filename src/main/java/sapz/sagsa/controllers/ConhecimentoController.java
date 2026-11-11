package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapz.sagsa.models.dtos.ConhecimentoDTO;
import sapz.sagsa.services.ConhecimentoService;

import java.util.List;

@RestController
@RequestMapping("/api/conhecimentos")
@RequiredArgsConstructor
public class ConhecimentoController {

    private final ConhecimentoService conhecimentoService;

    @GetMapping("/unidade-curricular/{ucId}")
    public ResponseEntity<List<ConhecimentoDTO>> listarPorUnidadeCurricular(@PathVariable Long ucId) {
        return ResponseEntity.ok(conhecimentoService.listarPorUnidadeCurricularId(ucId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConhecimentoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(conhecimentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ConhecimentoDTO> criar(@Valid @RequestBody ConhecimentoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conhecimentoService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConhecimentoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ConhecimentoDTO dto) {
        return ResponseEntity.ok(conhecimentoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        conhecimentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}