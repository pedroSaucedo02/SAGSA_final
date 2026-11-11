package sapz.sagsa.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sapz.sagsa.models.Perfil;
import sapz.sagsa.services.PerfilService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/perfis")
@Validated
public class PerfilController {
    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public ResponseEntity<List<Perfil>> findAll() {
        return ResponseEntity.ok(perfilService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> findById(@PathVariable Long id) {
        return ResponseEntity.ok(perfilService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Perfil perfil) {
        perfil.setId(null);
        Perfil created = perfilService.create(perfil);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Perfil perfil, @PathVariable Long id) {
        perfil.setId(id);
        perfilService.update(id, perfil);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        perfilService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
