package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapz.sagsa.models.dtos.EscolaDTO;
import sapz.sagsa.services.EscolaService;

import java.util.List;

@RestController
@RequestMapping("/api/escolas")
@RequiredArgsConstructor
public class EscolaController {

    private final EscolaService escolaService;

    @GetMapping
    public ResponseEntity<List<EscolaDTO>> listarTodas() {
        return ResponseEntity.ok(escolaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscolaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(escolaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EscolaDTO> criar(@Valid @RequestBody EscolaDTO dto) {
        EscolaDTO criada = escolaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscolaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody EscolaDTO dto) {
        return ResponseEntity.ok(escolaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        escolaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}