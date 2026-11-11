package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapz.sagsa.models.dtos.AreaTecnologicaDTO;
import sapz.sagsa.services.AreaTecnologicaService;

import java.util.List;

@RestController
@RequestMapping("/api/areas-tecnologicas")
@RequiredArgsConstructor
public class AreaTecnologicaController {

    private final AreaTecnologicaService areaTecnologicaService;

    @GetMapping
    public ResponseEntity<List<AreaTecnologicaDTO>> listarTodas() {
        return ResponseEntity.ok(areaTecnologicaService.listarTodas());
    }

    @GetMapping("/eixo/{eixoId}")
    public ResponseEntity<List<AreaTecnologicaDTO>> listarPorEixo(@PathVariable Long eixoId) {
        return ResponseEntity.ok(areaTecnologicaService.listarPorEixoId(eixoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaTecnologicaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(areaTecnologicaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AreaTecnologicaDTO> criar(@Valid @RequestBody AreaTecnologicaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(areaTecnologicaService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaTecnologicaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AreaTecnologicaDTO dto) {
        return ResponseEntity.ok(areaTecnologicaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        areaTecnologicaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}