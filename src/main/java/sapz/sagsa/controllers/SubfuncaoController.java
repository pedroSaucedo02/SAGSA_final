package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapz.sagsa.models.dtos.SubfuncaoDTO;
import sapz.sagsa.services.SubfuncaoService;

import java.util.List;

@RestController
@RequestMapping("/api/subfuncoes")
@RequiredArgsConstructor
public class SubfuncaoController {

    private final SubfuncaoService subfuncaoService;

    @GetMapping("/funcao/{funcaoId}")
    public ResponseEntity<List<SubfuncaoDTO>> listarPorFuncao(@PathVariable Long funcaoId) {
        return ResponseEntity.ok(subfuncaoService.listarPorFuncaoId(funcaoId));
    }

    @PostMapping
    public ResponseEntity<SubfuncaoDTO> criar(@Valid @RequestBody SubfuncaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subfuncaoService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubfuncaoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody SubfuncaoDTO dto) {
        return ResponseEntity.ok(subfuncaoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        subfuncaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}