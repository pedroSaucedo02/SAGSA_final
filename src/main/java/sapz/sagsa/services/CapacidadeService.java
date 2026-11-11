package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.Capacidade;
import sapz.sagsa.models.UnidadeCurricular;
import sapz.sagsa.models.dtos.CapacidadeDTO;
import sapz.sagsa.repositories.CapacidadeRepository;
import sapz.sagsa.repositories.UnidadeCurricularRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CapacidadeService {

    private final CapacidadeRepository capacidadeRepository;
    private final UnidadeCurricularRepository ucRepository;

    @Transactional(readOnly = true)
    public List<CapacidadeDTO> listarPorUnidadeCurricularId(Long ucId) {
        return capacidadeRepository.findAll().stream()
                .filter(c -> c.getUnidadeCurricular() != null && c.getUnidadeCurricular().getId().equals(ucId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CapacidadeDTO buscarPorId(Long id) {
        Capacidade capacidade = capacidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Capacidade não encontrada com o ID: " + id));
        return toDTO(capacidade);
    }

    @Transactional
    public CapacidadeDTO criar(CapacidadeDTO dto) {
        UnidadeCurricular uc = ucRepository.findById(dto.getUnidadeCurricularId())
                .orElseThrow(() -> new EntityNotFoundException("Unidade Curricular não encontrada com o ID: " + dto.getUnidadeCurricularId()));

        Capacidade capacidade = new Capacidade();
        capacidade.setTipo(dto.getTipo());
        capacidade.setDominio(dto.getDominio());
        capacidade.setDescricao(dto.getDescricao());
        capacidade.setUnidadeCurricular(uc);

        return toDTO(capacidadeRepository.save(capacidade));
    }

    @Transactional
    public CapacidadeDTO atualizar(Long id, CapacidadeDTO dto) {
        Capacidade capacidade = capacidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Capacidade não encontrada com o ID: " + id));
        UnidadeCurricular uc = ucRepository.findById(dto.getUnidadeCurricularId())
                .orElseThrow(() -> new EntityNotFoundException("Unidade Curricular não encontrada com o ID: " + dto.getUnidadeCurricularId()));

        capacidade.setTipo(dto.getTipo());
        capacidade.setDominio(dto.getDominio());
        capacidade.setDescricao(dto.getDescricao());
        capacidade.setUnidadeCurricular(uc);

        return toDTO(capacidadeRepository.save(capacidade));
    }

    @Transactional
    public void deletar(Long id) {
        if (!capacidadeRepository.existsById(id)) {
            throw new EntityNotFoundException("Capacidade não encontrada com o ID: " + id);
        }
        capacidadeRepository.deleteById(id);
    }

    private CapacidadeDTO toDTO(Capacidade c) {
        return CapacidadeDTO.builder()
                .id(c.getId())
                .tipo(c.getTipo())
                .dominio(c.getDominio())
                .descricao(c.getDescricao())
                .unidadeCurricularId(c.getUnidadeCurricular() != null ? c.getUnidadeCurricular().getId() : null)
                .build();
    }
}