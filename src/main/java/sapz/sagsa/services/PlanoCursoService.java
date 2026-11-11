package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.Curso;
import sapz.sagsa.models.PlanoCurso;
import sapz.sagsa.models.dtos.PlanoCursoDTO;
import sapz.sagsa.repositories.CursoRepository;
import sapz.sagsa.repositories.PlanoCursoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanoCursoService {

    private final PlanoCursoRepository planoCursoRepository;
    private final CursoRepository cursoRepository;

    @Transactional(readOnly = true)
    public List<PlanoCursoDTO> listarTodos() {
        return planoCursoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PlanoCursoDTO buscarPorId(Long id) {
        PlanoCurso plano = planoCursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano de curso não encontrado com ID: " + id));
        return toDTO(plano);
    }

    @Transactional
    public PlanoCursoDTO criar(PlanoCursoDTO dto) {
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com ID: " + dto.getCursoId()));

        PlanoCurso plano = new PlanoCurso();
        plano.setCodigoPlano(dto.getCodigoPlano());
        plano.setVersao(dto.getVersao() != null ? dto.getVersao() : "1.0");
        plano.setCargaHorariaTotal(dto.getCargaHorariaTotal());
        plano.setDataAprovacao(dto.getDataAprovacao());
        if (dto.getAtivo() != null) plano.setAtivo(dto.getAtivo());
        plano.setCurso(curso);

        return toDTO(planoCursoRepository.save(plano));
    }

    @Transactional
    public PlanoCursoDTO atualizar(Long id, PlanoCursoDTO dto) {
        PlanoCurso plano = planoCursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano de curso não encontrado com ID: " + id));
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com ID: " + dto.getCursoId()));

        plano.setCodigoPlano(dto.getCodigoPlano());
        plano.setVersao(dto.getVersao());
        plano.setCargaHorariaTotal(dto.getCargaHorariaTotal());
        plano.setDataAprovacao(dto.getDataAprovacao());
        if (dto.getAtivo() != null) plano.setAtivo(dto.getAtivo());
        plano.setCurso(curso);

        return toDTO(planoCursoRepository.save(plano));
    }

    @Transactional
    public void deletar(Long id) {
        if (!planoCursoRepository.existsById(id)) {
            throw new EntityNotFoundException("Plano de curso não encontrado com ID: " + id);
        }
        planoCursoRepository.deleteById(id);
    }

    private PlanoCursoDTO toDTO(PlanoCurso p) {
        return PlanoCursoDTO.builder()
                .id(p.getId())
                .codigoPlano(p.getCodigoPlano())
                .versao(p.getVersao())
                .cargaHorariaTotal(p.getCargaHorariaTotal())
                .dataAprovacao(p.getDataAprovacao())
                .ativo(p.getAtivo())
                .cursoId(p.getCurso() != null ? p.getCurso().getId() : null)
                .cursoNome(p.getCurso() != null ? p.getCurso().getNome() : null)
                .build();
    }
}