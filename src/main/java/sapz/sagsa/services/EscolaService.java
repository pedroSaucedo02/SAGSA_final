package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.Escola;
import sapz.sagsa.models.dtos.EscolaDTO;
import sapz.sagsa.repositories.EscolaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EscolaService {

    private final EscolaRepository escolaRepository;

    @Transactional(readOnly = true)
    public List<EscolaDTO> listarTodas() {
        return escolaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EscolaDTO buscarPorId(Long id) {
        Escola escola = escolaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Escola não encontrada com o ID: " + id));
        return toDTO(escola);
    }

    @Transactional
    public EscolaDTO criar(EscolaDTO dto) {
        Escola escola = new Escola();
        escola.setNome(dto.getNome());
        escola.setCodigoUnidade(dto.getCodigo());
        escola.setCidade(dto.getCidade());
        if (dto.getUf() != null) {
            escola.setEstado(dto.getUf());
        }
        return toDTO(escolaRepository.save(escola));
    }

    @Transactional
    public EscolaDTO atualizar(Long id, EscolaDTO dto) {
        Escola escola = escolaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Escola não encontrada com o ID: " + id));
        escola.setNome(dto.getNome());
        escola.setCodigoUnidade(dto.getCodigo());
        escola.setCidade(dto.getCidade());
        if (dto.getUf() != null) {
            escola.setEstado(dto.getUf());
        }
        return toDTO(escolaRepository.save(escola));
    }

    @Transactional
    public void deletar(Long id) {
        if (!escolaRepository.existsById(id)) {
            throw new EntityNotFoundException("Escola não encontrada com o ID: " + id);
        }
        escolaRepository.deleteById(id);
    }

    private EscolaDTO toDTO(Escola escola) {
        return EscolaDTO.builder()
                .id(escola.getId())
                .nome(escola.getNome())
                .codigo(escola.getCodigoUnidade())
                .cidade(escola.getCidade())
                .uf(escola.getEstado())
                .build();
    }
}