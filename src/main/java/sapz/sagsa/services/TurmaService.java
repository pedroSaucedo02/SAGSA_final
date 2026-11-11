package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.Escola;
import sapz.sagsa.models.PlanoCurso;
import sapz.sagsa.models.Turma;
import sapz.sagsa.models.dtos.TurmaDTO;
import sapz.sagsa.repositories.EscolaRepository;
import sapz.sagsa.repositories.PlanoCursoRepository;
import sapz.sagsa.repositories.TurmaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final EscolaRepository escolaRepository;
    private final PlanoCursoRepository planoCursoRepository;

    @Transactional(readOnly = true)
    public List<TurmaDTO> listarTodas() {
        return turmaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TurmaDTO> listarPorEscolaId(Long escolaId) {
        return turmaRepository.findAll().stream()
                .filter(t -> t.getEscola() != null && t.getEscola().getId().equals(escolaId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TurmaDTO buscarPorId(Long id) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com o ID: " + id));
        return toDTO(turma);
    }

    @Transactional
    public TurmaDTO criar(TurmaDTO dto) {
        Escola escola = escolaRepository.findById(dto.getEscolaId())
                .orElseThrow(() -> new EntityNotFoundException("Escola não encontrada com o ID: " + dto.getEscolaId()));
        PlanoCurso plano = planoCursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Plano de Curso não encontrado com o ID: " + dto.getCursoId()));

        Turma turma = new Turma();
        turma.setCodigoTurma(dto.getCodigo());
        turma.setDataInicio(dto.getDataInicio());
        turma.setDataFim(dto.getDataFim());
        turma.setEscola(escola);
        turma.setPlanoCurso(plano);

        return toDTO(turmaRepository.save(turma));
    }

    @Transactional
    public TurmaDTO atualizar(Long id, TurmaDTO dto) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com o ID: " + id));
        Escola escola = escolaRepository.findById(dto.getEscolaId())
                .orElseThrow(() -> new EntityNotFoundException("Escola não encontrada com o ID: " + dto.getEscolaId()));
        PlanoCurso plano = planoCursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Plano de Curso não encontrado com o ID: " + dto.getCursoId()));

        turma.setCodigoTurma(dto.getCodigo());
        turma.setDataInicio(dto.getDataInicio());
        turma.setDataFim(dto.getDataFim());
        turma.setEscola(escola);
        turma.setPlanoCurso(plano);

        return toDTO(turmaRepository.save(turma));
    }

    @Transactional
    public void deletar(Long id) {
        if (!turmaRepository.existsById(id)) {
            throw new EntityNotFoundException("Turma não encontrada com o ID: " + id);
        }
        turmaRepository.deleteById(id);
    }

    private TurmaDTO toDTO(Turma turma) {
        return TurmaDTO.builder()
                .id(turma.getId())
                .codigo(turma.getCodigoTurma())
                .dataInicio(turma.getDataInicio())
                .dataFim(turma.getDataFim())
                .escolaId(turma.getEscola() != null ? turma.getEscola().getId() : null)
                .cursoId(turma.getPlanoCurso() != null ? turma.getPlanoCurso().getId() : null)
                .build();
    }
}