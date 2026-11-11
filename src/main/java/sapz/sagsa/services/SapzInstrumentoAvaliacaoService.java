package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.SapzInstrumentoAvaliacao;
import sapz.sagsa.models.SituacaoAprendizagem;
import sapz.sagsa.models.dtos.SapzInstrumentoAvaliacaoDTO;
import sapz.sagsa.repositories.SapzInstrumentoAvaliacaoRepository;
import sapz.sagsa.repositories.SituacaoAprendizagemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SapzInstrumentoAvaliacaoService {

    private final SapzInstrumentoAvaliacaoRepository instrumentoRepository;
    private final SituacaoAprendizagemRepository saRepository;

    @Transactional(readOnly = true)
    public List<SapzInstrumentoAvaliacaoDTO> listarPorSituacaoAprendizagemId(Long saId) {
        return instrumentoRepository.findAll().stream()
                .filter(i -> i.getSituacaoAprendizagem() != null && i.getSituacaoAprendizagem().getId().equals(saId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public SapzInstrumentoAvaliacaoDTO criar(SapzInstrumentoAvaliacaoDTO dto) {
        SituacaoAprendizagem sa = saRepository.findById(dto.getSituacaoAprendizagemId())
                .orElseThrow(() -> new EntityNotFoundException("Situação de Aprendizagem não encontrada com o ID: " + dto.getSituacaoAprendizagemId()));

        SapzInstrumentoAvaliacao inst = new SapzInstrumentoAvaliacao();
        inst.setNomeInstrumento(dto.getNomeInstrumento());
        inst.setDescricao(dto.getDescricao());
        if (dto.getTipoMetodo() != null) inst.setTipoMetodo(dto.getTipoMetodo());
        inst.setSituacaoAprendizagem(sa);

        return toDTO(instrumentoRepository.save(inst));
    }

    @Transactional
    public SapzInstrumentoAvaliacaoDTO atualizar(Long id, SapzInstrumentoAvaliacaoDTO dto) {
        SapzInstrumentoAvaliacao inst = instrumentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instrumento de avaliação não encontrado com o ID: " + id));
        SituacaoAprendizagem sa = saRepository.findById(dto.getSituacaoAprendizagemId())
                .orElseThrow(() -> new EntityNotFoundException("Situação de Aprendizagem não encontrada com o ID: " + dto.getSituacaoAprendizagemId()));

        inst.setNomeInstrumento(dto.getNomeInstrumento());
        inst.setDescricao(dto.getDescricao());
        if (dto.getTipoMetodo() != null) inst.setTipoMetodo(dto.getTipoMetodo());
        inst.setSituacaoAprendizagem(sa);

        return toDTO(instrumentoRepository.save(inst));
    }

    @Transactional
    public void deletar(Long id) {
        if (!instrumentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Instrumento de avaliação não encontrado com o ID: " + id);
        }
        instrumentoRepository.deleteById(id);
    }

    private SapzInstrumentoAvaliacaoDTO toDTO(SapzInstrumentoAvaliacao inst) {
        return SapzInstrumentoAvaliacaoDTO.builder()
                .id(inst.getId())
                .nomeInstrumento(inst.getNomeInstrumento())
                .descricao(inst.getDescricao())
                .tipoMetodo(inst.getTipoMetodo())
                .situacaoAprendizagemId(inst.getSituacaoAprendizagem() != null ? inst.getSituacaoAprendizagem().getId() : null)
                .build();
    }
}