package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.Conhecimento;
import sapz.sagsa.models.UnidadeCurricular;
import sapz.sagsa.models.dtos.ConhecimentoDTO;
import sapz.sagsa.repositories.ConhecimentoRepository;
import sapz.sagsa.repositories.UnidadeCurricularRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConhecimentoService {

    private final ConhecimentoRepository conhecimentoRepository;
    private final UnidadeCurricularRepository ucRepository;

    @Transactional(readOnly = true)
    public List<ConhecimentoDTO> listarPorUnidadeCurricularId(Long ucId) {
        return conhecimentoRepository.findAll().stream()
                .filter(c -> c.getUnidadeCurricular() != null && c.getUnidadeCurricular().getId().equals(ucId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ConhecimentoDTO buscarPorId(Long id) {
        Conhecimento c = conhecimentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conhecimento não encontrado com o ID: " + id));
        return toDTO(c);
    }

    @Transactional
    public ConhecimentoDTO criar(ConhecimentoDTO dto) {
        UnidadeCurricular uc = ucRepository.findById(dto.getUnidadeCurricularId())
                .orElseThrow(() -> new EntityNotFoundException("Unidade Curricular não encontrada com o ID: " + dto.getUnidadeCurricularId()));

        Conhecimento c = new Conhecimento();
        c.setTopico(dto.getTopico());
        c.setDetalhamento(dto.getDetalhamento());
        c.setUnidadeCurricular(uc);

        return toDTO(conhecimentoRepository.save(c));
    }

    @Transactional
    public ConhecimentoDTO atualizar(Long id, ConhecimentoDTO dto) {
        Conhecimento c = conhecimentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conhecimento não encontrado com o ID: " + id));
        UnidadeCurricular uc = ucRepository.findById(dto.getUnidadeCurricularId())
                .orElseThrow(() -> new EntityNotFoundException("Unidade Curricular não encontrada com o ID: " + dto.getUnidadeCurricularId()));

        c.setTopico(dto.getTopico());
        c.setDetalhamento(dto.getDetalhamento());
        c.setUnidadeCurricular(uc);

        return toDTO(conhecimentoRepository.save(c));
    }

    @Transactional
    public void deletar(Long id) {
        if (!conhecimentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Conhecimento não encontrado com o ID: " + id);
        }
        conhecimentoRepository.deleteById(id);
    }

    private ConhecimentoDTO toDTO(Conhecimento c) {
        return ConhecimentoDTO.builder()
                .id(c.getId())
                .topico(c.getTopico())
                .detalhamento(c.getDetalhamento())
                .unidadeCurricularId(c.getUnidadeCurricular() != null ? c.getUnidadeCurricular().getId() : null)
                .build();
    }
}