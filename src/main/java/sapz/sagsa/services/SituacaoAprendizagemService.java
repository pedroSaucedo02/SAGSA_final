package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.SituacaoAprendizagem;
import sapz.sagsa.models.Turma;
import sapz.sagsa.models.UnidadeCurricular;
import sapz.sagsa.models.Usuario;
import sapz.sagsa.models.dtos.SituacaoAprendizagemDTO;
import sapz.sagsa.repositories.SituacaoAprendizagemRepository;
import sapz.sagsa.repositories.TurmaRepository;
import sapz.sagsa.repositories.UnidadeCurricularRepository;
import sapz.sagsa.repositories.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SituacaoAprendizagemService {

    private final SituacaoAprendizagemRepository saRepository;
    private final UnidadeCurricularRepository ucRepository;
    private final UsuarioRepository usuarioRepository;
    private final TurmaRepository turmaRepository;

    @Transactional(readOnly = true)
    public List<SituacaoAprendizagemDTO> listarTodas() {
        return saRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SituacaoAprendizagemDTO buscarPorId(Long id) {
        SituacaoAprendizagem sa = saRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SituacaoAprendizagem não encontrada com ID: " + id));
        return toDTO(sa);
    }

    @Transactional
    public SituacaoAprendizagemDTO criar(SituacaoAprendizagemDTO dto) {
        UnidadeCurricular uc = ucRepository.findById(dto.getUnidadeCurricularId())
                .orElseThrow(() -> new EntityNotFoundException("Unidade Curricular não encontrada com ID: " + dto.getUnidadeCurricularId()));
        Usuario instrutor = usuarioRepository.findById(dto.getInstrutorId())
                .orElseThrow(() -> new EntityNotFoundException("Instrutor não encontrado com ID: " + dto.getInstrutorId()));

        SituacaoAprendizagem sa = new SituacaoAprendizagem();
        sa.setTitulo(dto.getTitulo());
        sa.setUnidadeCurricular(uc);
        sa.setInstrutor(instrutor);
        sa.setTipoEstrategia(dto.getTipoEstrategia());
        sa.setContextualizacao(dto.getContextualizacao());
        sa.setDesafio(dto.getDesafio());
        sa.setResultadosEsperados(dto.getResultadosEsperados());
        sa.setCargaHorariaSa(dto.getCargaHorariaSa());
        if (dto.getCompartilhada() != null) sa.setCompartilhada(dto.getCompartilhada());

        if (dto.getTurmaId() != null) {
            Turma turma = turmaRepository.findById(dto.getTurmaId())
                    .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com ID: " + dto.getTurmaId()));
            sa.setTurma(turma);
        }

        return toDTO(saRepository.save(sa));
    }

    @Transactional
    public SituacaoAprendizagemDTO atualizar(Long id, SituacaoAprendizagemDTO dto) {
        SituacaoAprendizagem sa = saRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SituacaoAprendizagem não encontrada com ID: " + id));
        UnidadeCurricular uc = ucRepository.findById(dto.getUnidadeCurricularId())
                .orElseThrow(() -> new EntityNotFoundException("Unidade Curricular não encontrada com ID: " + dto.getUnidadeCurricularId()));
        Usuario instrutor = usuarioRepository.findById(dto.getInstrutorId())
                .orElseThrow(() -> new EntityNotFoundException("Instrutor não encontrado com ID: " + dto.getInstrutorId()));

        sa.setTitulo(dto.getTitulo());
        sa.setUnidadeCurricular(uc);
        sa.setInstrutor(instrutor);
        sa.setTipoEstrategia(dto.getTipoEstrategia());
        sa.setContextualizacao(dto.getContextualizacao());
        sa.setDesafio(dto.getDesafio());
        sa.setResultadosEsperados(dto.getResultadosEsperados());
        sa.setCargaHorariaSa(dto.getCargaHorariaSa());
        if (dto.getCompartilhada() != null) sa.setCompartilhada(dto.getCompartilhada());

        if (dto.getTurmaId() != null) {
            Turma turma = turmaRepository.findById(dto.getTurmaId())
                    .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com ID: " + dto.getTurmaId()));
            sa.setTurma(turma);
        } else {
            sa.setTurma(null);
        }

        return toDTO(saRepository.save(sa));
    }

    @Transactional
    public void deletar(Long id) {
        if (!saRepository.existsById(id)) {
            throw new EntityNotFoundException("SituacaoAprendizagem não encontrada com ID: " + id);
        }
        saRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<SituacaoAprendizagemDTO> listarPorInstrutorId(Long instrutorId) {
        return saRepository.findAll().stream()
                .filter(sa -> sa.getInstrutor() != null && sa.getInstrutor().getId().equals(instrutorId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    private SituacaoAprendizagemDTO toDTO(SituacaoAprendizagem sa) {
        return SituacaoAprendizagemDTO.builder()
                .id(sa.getId())
                .titulo(sa.getTitulo())
                .unidadeCurricularId(sa.getUnidadeCurricular() != null ? sa.getUnidadeCurricular().getId() : null)
                .instrutorId(sa.getInstrutor() != null ? sa.getInstrutor().getId() : null)
                .turmaId(sa.getTurma() != null ? sa.getTurma().getId() : null)
                .tipoEstrategia(sa.getTipoEstrategia())
                .contextualizacao(sa.getContextualizacao())
                .desafio(sa.getDesafio())
                .resultadosEsperados(sa.getResultadosEsperados())
                .cargaHorariaSa(sa.getCargaHorariaSa())
                .compartilhada(sa.getCompartilhada())
                .dataCriacao(sa.getDataCriacao())
                .dataAtualizacao(sa.getDataAtualizacao())
                .build();
    }
}