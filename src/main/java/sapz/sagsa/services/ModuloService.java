package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.Modulo;
import sapz.sagsa.models.PlanoCurso;
import sapz.sagsa.models.dtos.ModuloDTO;
import sapz.sagsa.repositories.ModuloRepository;
import sapz.sagsa.repositories.PlanoCursoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModuloService {

    private final ModuloRepository moduloRepository;
    private final PlanoCursoRepository planoCursoRepository;

    @Transactional(readOnly = true)
    public List<ModuloDTO> listarPorPlanoCursoId(Long planoId) {
        return moduloRepository.findAll().stream()
                .filter(m -> m.getPlanoCurso() != null && m.getPlanoCurso().getId().equals(planoId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ModuloDTO buscarPorId(Long id) {
        Modulo modulo = moduloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Módulo não encontrado com o ID: " + id));
        return toDTO(modulo);
    }

    @Transactional
    public ModuloDTO criar(ModuloDTO dto) {
        PlanoCurso plano = planoCursoRepository.findById(dto.getPlanoCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Plano de curso não encontrado com o ID: " + dto.getPlanoCursoId()));

        Modulo modulo = new Modulo();
        modulo.setNome(dto.getNome());
        modulo.setTipoModulo(dto.getTipoModulo());
        modulo.setOrdem(dto.getOrdem() != null ? dto.getOrdem() : 1);
        modulo.setCargaHoraria(dto.getCargaHoraria());
        modulo.setPlanoCurso(plano);

        return toDTO(moduloRepository.save(modulo));
    }

    @Transactional
    public ModuloDTO atualizar(Long id, ModuloDTO dto) {
        Modulo modulo = moduloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Módulo não encontrado com o ID: " + id));
        PlanoCurso plano = planoCursoRepository.findById(dto.getPlanoCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Plano de curso não encontrado com o ID: " + dto.getPlanoCursoId()));

        modulo.setNome(dto.getNome());
        modulo.setTipoModulo(dto.getTipoModulo());
        if (dto.getOrdem() != null) modulo.setOrdem(dto.getOrdem());
        modulo.setCargaHoraria(dto.getCargaHoraria());
        modulo.setPlanoCurso(plano);

        return toDTO(moduloRepository.save(modulo));
    }

    @Transactional
    public void deletar(Long id) {
        if (!moduloRepository.existsById(id)) {
            throw new EntityNotFoundException("Módulo não encontrado com o ID: " + id);
        }
        moduloRepository.deleteById(id);
    }

    private ModuloDTO toDTO(Modulo modulo) {
        return ModuloDTO.builder()
                .id(modulo.getId())
                .nome(modulo.getNome())
                .tipoModulo(modulo.getTipoModulo())
                .ordem(modulo.getOrdem())
                .cargaHoraria(modulo.getCargaHoraria())
                .planoCursoId(modulo.getPlanoCurso() != null ? modulo.getPlanoCurso().getId() : null)
                .build();
    }
}