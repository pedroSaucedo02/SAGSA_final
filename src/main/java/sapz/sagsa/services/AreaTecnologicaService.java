package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.AreaTecnologica;
import sapz.sagsa.models.EixoTecnologico;
import sapz.sagsa.models.dtos.AreaTecnologicaDTO;
import sapz.sagsa.repositories.AreaTecnologicaRepository;
import sapz.sagsa.repositories.EixoTecnologicoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AreaTecnologicaService {

    private final AreaTecnologicaRepository areaRepository;
    private final EixoTecnologicoRepository eixoRepository;

    @Transactional(readOnly = true)
    public List<AreaTecnologicaDTO> listarTodas() {
        return areaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AreaTecnologicaDTO> listarPorEixoId(Long eixoId) {
        return areaRepository.findAll().stream()
                .filter(a -> a.getEixoTecnologico() != null && a.getEixoTecnologico().getId().equals(eixoId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AreaTecnologicaDTO buscarPorId(Long id) {
        AreaTecnologica area = areaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Área Tecnológica não encontrada com o ID: " + id));
        return toDTO(area);
    }

    @Transactional
    public AreaTecnologicaDTO criar(AreaTecnologicaDTO dto) {
        EixoTecnologico eixo = eixoRepository.findById(dto.getEixoTecnologicoId())
                .orElseThrow(() -> new EntityNotFoundException("Eixo Tecnológico não encontrado com o ID: " + dto.getEixoTecnologicoId()));
        
        AreaTecnologica area = new AreaTecnologica();
        area.setNome(dto.getNome());
        area.setEixoTecnologico(eixo);
        return toDTO(areaRepository.save(area));
    }

    @Transactional
    public AreaTecnologicaDTO atualizar(Long id, AreaTecnologicaDTO dto) {
        AreaTecnologica area = areaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Área Tecnológica não encontrada com o ID: " + id));
        EixoTecnologico eixo = eixoRepository.findById(dto.getEixoTecnologicoId())
                .orElseThrow(() -> new EntityNotFoundException("Eixo Tecnológico não encontrado com o ID: " + dto.getEixoTecnologicoId()));

        area.setNome(dto.getNome());
        area.setEixoTecnologico(eixo);
        return toDTO(areaRepository.save(area));
    }

    @Transactional
    public void deletar(Long id) {
        if (!areaRepository.existsById(id)) {
            throw new EntityNotFoundException("Área Tecnológica não encontrada com o ID: " + id);
        }
        areaRepository.deleteById(id);
    }

    private AreaTecnologicaDTO toDTO(AreaTecnologica area) {
        return AreaTecnologicaDTO.builder()
                .id(area.getId())
                .nome(area.getNome())
                .eixoTecnologicoId(area.getEixoTecnologico() != null ? area.getEixoTecnologico().getId() : null)
                .build();
    }
}