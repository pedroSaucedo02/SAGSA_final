package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapz.sagsa.models.dtos.FuncaoDTO;
import sapz.sagsa.services.FuncaoService;

import java.util.List;

@RestController
@RequestMapping("/api/funcoes")
@RequiredArgsConstructor
public class FuncaoController {

    private final FuncaoService funcaoService;

    @GetMapping("/plano/{planoId}")
    public ResponseEntity<List<FuncaoDTO>> listarPorPlanoCurso(@PathVariable Long planoId) {
        return ResponseEntity.ok(funcaoService.listarPorPlanoCursoId(planoId));
    }

    @PostMapping
    public ResponseEntity<FuncaoDTO> criar(@Valid @RequestBody FuncaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(funcaoService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncaoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody FuncaoDTO dto) {
        return ResponseEntity.ok(funcaoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}