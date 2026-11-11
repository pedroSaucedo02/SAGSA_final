package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapz.sagsa.models.dtos.EixoTecnologicoDTO;
import sapz.sagsa.services.EixoTecnologicoService;

import java.util.List;

@RestController
@RequestMapping("/api/eixos-tecnologicos")
@RequiredArgsConstructor
public class EixoTecnologicoController {

    private final EixoTecnologicoService eixoTecnologicoService;

    @GetMapping
    public ResponseEntity<List<EixoTecnologicoDTO>> listarTodos() {
        return ResponseEntity.ok(eixoTecnologicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EixoTecnologicoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(eixoTecnologicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EixoTecnologicoDTO> criar(@Valid @RequestBody EixoTecnologicoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eixoTecnologicoService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EixoTecnologicoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody EixoTecnologicoDTO dto) {
        return ResponseEntity.ok(eixoTecnologicoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        eixoTecnologicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}