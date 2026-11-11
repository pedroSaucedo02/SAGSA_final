package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.Modulo;
import sapz.sagsa.models.UnidadeCurricular;
import sapz.sagsa.models.dtos.UnidadeCurricularRequestDTO;
import sapz.sagsa.models.dtos.UnidadeCurricularResponseDTO;
import sapz.sagsa.repositories.ModuloRepository;
import sapz.sagsa.repositories.UnidadeCurricularRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnidadeCurricularService {

    private final UnidadeCurricularRepository ucRepository;
    private final ModuloRepository moduloRepository;

    @Transactional(readOnly = true)
    public List<UnidadeCurricularResponseDTO> listarTodas() {
        return ucRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UnidadeCurricularResponseDTO> listarPorModuloId(Long moduloId) {
        return ucRepository.findAll().stream()
                .filter(u -> u.getModulo() != null && u.getModulo().getId().equals(moduloId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UnidadeCurricularResponseDTO buscarPorId(Long id) {
        UnidadeCurricular uc = ucRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unidade Curricular não encontrada com o ID: " + id));
        return toDTO(uc);
    }

    @Transactional
    public UnidadeCurricularResponseDTO criar(UnidadeCurricularRequestDTO dto) {
        Modulo modulo = moduloRepository.findById(dto.getModuloId())
                .orElseThrow(() -> new EntityNotFoundException("Módulo não encontrado com o ID: " + dto.getModuloId()));

        UnidadeCurricular uc = new UnidadeCurricular();
        uc.setNome(dto.getNome());
        uc.setCargaHoraria(dto.getCargaHoraria());
        uc.setModulo(modulo);

        return toDTO(ucRepository.save(uc));
    }

    @Transactional
    public UnidadeCurricularResponseDTO atualizar(Long id, UnidadeCurricularRequestDTO dto) {
        UnidadeCurricular uc = ucRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unidade Curricular não encontrada com o ID: " + id));
        Modulo modulo = moduloRepository.findById(dto.getModuloId())
                .orElseThrow(() -> new EntityNotFoundException("Módulo não encontrado com o ID: " + dto.getModuloId()));

        uc.setNome(dto.getNome());
        uc.setCargaHoraria(dto.getCargaHoraria());
        uc.setModulo(modulo);

        return toDTO(ucRepository.save(uc));
    }

    @Transactional
    public void deletar(Long id) {
        if (!ucRepository.existsById(id)) {
            throw new EntityNotFoundException("Unidade Curricular não encontrada com o ID: " + id);
        }
        ucRepository.deleteById(id);
    }

    private UnidadeCurricularResponseDTO toDTO(UnidadeCurricular uc) {
        return UnidadeCurricularResponseDTO.builder()
                .id(uc.getId())
                .nome(uc.getNome())
                .cargaHoraria(uc.getCargaHoraria())
                .moduloId(uc.getModulo() != null ? uc.getModulo().getId() : null)
                .build();
    }
}