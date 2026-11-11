package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.Escola;
import sapz.sagsa.models.PlanoCurso;
import sapz.sagsa.models.Turma;
import sapz.sagsa.models.Usuario;
import sapz.sagsa.models.dtos.TurmaDTO;
import sapz.sagsa.repositories.EscolaRepository;
import sapz.sagsa.repositories.PlanoCursoRepository;
import sapz.sagsa.repositories.TurmaRepository;
import sapz.sagsa.repositories.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final EscolaRepository escolaRepository;
    private final PlanoCursoRepository planoCursoRepository;
    private final UsuarioRepository usuarioRepository;

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
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com ID: " + id));
        return toDTO(turma);
    }

    @Transactional
    public TurmaDTO criar(TurmaDTO dto) {
        Escola escola = escolaRepository.findById(dto.getEscolaId())
                .orElseThrow(() -> new EntityNotFoundException("Escola não encontrada com ID: " + dto.getEscolaId()));
        PlanoCurso plano = planoCursoRepository.findById(dto.getPlanoCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Plano de Curso não encontrado com ID: " + dto.getPlanoCursoId()));

        Turma turma = new Turma();
        turma.setCodigoTurma(dto.getCodigoTurma());
        turma.setAno(dto.getAno());
        turma.setSemestre(dto.getSemestre());
        turma.setDataInicio(dto.getDataInicio());
        turma.setDataFim(dto.getDataFim());
        if (dto.getAtivo() != null) turma.setAtivo(dto.getAtivo());
        turma.setEscola(escola);
        turma.setPlanoCurso(plano);

        if (dto.getCoordenadorId() != null) {
            Usuario coordenador = usuarioRepository.findById(dto.getCoordenadorId())
                    .orElseThrow(() -> new EntityNotFoundException("Coordenador não encontrado com ID: " + dto.getCoordenadorId()));
            turma.setCoordenador(coordenador);
        }

        return toDTO(turmaRepository.save(turma));
    }

    @Transactional
    public TurmaDTO atualizar(Long id, TurmaDTO dto) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com ID: " + id));
        Escola escola = escolaRepository.findById(dto.getEscolaId())
                .orElseThrow(() -> new EntityNotFoundException("Escola não encontrada com ID: " + dto.getEscolaId()));
        PlanoCurso plano = planoCursoRepository.findById(dto.getPlanoCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Plano de Curso não encontrado com ID: " + dto.getPlanoCursoId()));

        turma.setCodigoTurma(dto.getCodigoTurma());
        turma.setAno(dto.getAno());
        turma.setSemestre(dto.getSemestre());
        turma.setDataInicio(dto.getDataInicio());
        turma.setDataFim(dto.getDataFim());
        if (dto.getAtivo() != null) turma.setAtivo(dto.getAtivo());
        turma.setEscola(escola);
        turma.setPlanoCurso(plano);

        if (dto.getCoordenadorId() != null) {
            Usuario coordenador = usuarioRepository.findById(dto.getCoordenadorId())
                    .orElseThrow(() -> new EntityNotFoundException("Coordenador não encontrado com ID: " + dto.getCoordenadorId()));
            turma.setCoordenador(coordenador);
        } else {
            turma.setCoordenador(null);
        }

        return toDTO(turmaRepository.save(turma));
    }

    @Transactional
    public void deletar(Long id) {
        if (!turmaRepository.existsById(id)) {
            throw new EntityNotFoundException("Turma não encontrada com ID: " + id);
        }
        turmaRepository.deleteById(id);
    }

    private TurmaDTO toDTO(Turma t) {
        return TurmaDTO.builder()
                .id(t.getId())
                .codigoTurma(t.getCodigoTurma())
                .ano(t.getAno())
                .semestre(t.getSemestre())
                .dataInicio(t.getDataInicio())
                .dataFim(t.getDataFim())
                .ativo(t.getAtivo())
                .escolaId(t.getEscola() != null ? t.getEscola().getId() : null)
                .planoCursoId(t.getPlanoCurso() != null ? t.getPlanoCurso().getId() : null)
                .coordenadorId(t.getCoordenador() != null ? t.getCoordenador().getId() : null)
                .build();
    }
}