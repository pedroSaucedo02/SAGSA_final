package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sapz.sagsa.models.PlanoCurso;
import sapz.sagsa.services.PlanoCursoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/planos-curso")
@Validated
public class PlanoCursoController {
    @Autowired
    private PlanoCursoService planoCursoService;

    @GetMapping
    public ResponseEntity<List<PlanoCurso>> findAll() {
        return ResponseEntity.ok(planoCursoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoCurso> findById(@PathVariable Long id) {
        return ResponseEntity.ok(planoCursoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody PlanoCurso planoCurso) {
        planoCurso.setId(null);
        PlanoCurso created = planoCursoService.create(planoCurso);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody PlanoCurso planoCurso, @PathVariable Long id) {
        planoCurso.setId(id);
        planoCursoService.update(id, planoCurso);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        planoCursoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
