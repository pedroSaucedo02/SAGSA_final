package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.*;
import sapz.sagsa.models.dtos.SapzCompartilhamentoDTO;
import sapz.sagsa.repositories.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SapzCompartilhamentoService {

    private final SapzCompartilhamentoRepository compartilhamentoRepository;
    private final SituacaoAprendizagemRepository saRepository;
    private final EscolaRepository escolaRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<SapzCompartilhamentoDTO> listarPorSituacaoAprendizagemId(Long saId) {
        return compartilhamentoRepository.findAll().stream()
                .filter(c -> c.getSituacaoAprendizagem() != null && c.getSituacaoAprendizagem().getId().equals(saId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public SapzCompartilhamentoDTO compartilhar(SapzCompartilhamentoDTO dto) {
        SituacaoAprendizagem sa = saRepository.findById(dto.getSituacaoAprendizagemId())
                .orElseThrow(() -> new EntityNotFoundException("Situação de Aprendizagem não encontrada com o ID: " + dto.getSituacaoAprendizagemId()));

        SapzCompartilhamento c = new SapzCompartilhamento();
        c.setSituacaoAprendizagem(sa);
        c.setTipoCompartilhamento(dto.getTipoCompartilhamento());
        c.setDataCompartilhamento(LocalDateTime.now());

        if (dto.getEscolaId() != null) {
            Escola escola = escolaRepository.findById(dto.getEscolaId())
                    .orElseThrow(() -> new EntityNotFoundException("Escola não encontrada com o ID: " + dto.getEscolaId()));
            c.setEscola(escola);
        }

        if (dto.getCursoId() != null) {
            Curso curso = cursoRepository.findById(dto.getCursoId())
                    .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com o ID: " + dto.getCursoId()));
            c.setCurso(curso);
        }

        if (dto.getUsuarioDestinatarioId() != null) {
            Usuario usr = usuarioRepository.findById(dto.getUsuarioDestinatarioId())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + dto.getUsuarioDestinatarioId()));
            c.setUsuarioDestinatario(usr);
        }

        return toDTO(compartilhamentoRepository.save(c));
    }

    @Transactional
    public void remover(Long id) {
        if (!compartilhamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Compartilhamento não encontrado com o ID: " + id);
        }
        compartilhamentoRepository.deleteById(id);
    }

    private SapzCompartilhamentoDTO toDTO(SapzCompartilhamento c) {
        return SapzCompartilhamentoDTO.builder()
                .id(c.getId())
                .situacaoAprendizagemId(c.getSituacaoAprendizagem() != null ? c.getSituacaoAprendizagem().getId() : null)
                .escolaId(c.getEscola() != null ? c.getEscola().getId() : null)
                .cursoId(c.getCurso() != null ? c.getCurso().getId() : null)
                .usuarioDestinatarioId(c.getUsuarioDestinatario() != null ? c.getUsuarioDestinatario().getId() : null)
                .tipoCompartilhamento(c.getTipoCompartilhamento())
                .dataCompartilhamento(c.getDataCompartilhamento())
                .build();
    }
}