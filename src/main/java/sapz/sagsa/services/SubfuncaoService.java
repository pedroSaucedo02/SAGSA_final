package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.Funcao;
import sapz.sagsa.models.Subfuncao;
import sapz.sagsa.models.dtos.SubfuncaoDTO;
import sapz.sagsa.repositories.FuncaoRepository;
import sapz.sagsa.repositories.SubfuncaoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubfuncaoService {

    private final SubfuncaoRepository subfuncaoRepository;
    private final FuncaoRepository funcaoRepository;

    @Transactional(readOnly = true)
    public List<SubfuncaoDTO> listarPorFuncaoId(Long funcaoId) {
        return subfuncaoRepository.findAll().stream()
                .filter(sf -> sf.getFuncao() != null && sf.getFuncao().getId().equals(funcaoId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public SubfuncaoDTO criar(SubfuncaoDTO dto) {
        Funcao funcao = funcaoRepository.findById(dto.getFuncaoId())
                .orElseThrow(() -> new EntityNotFoundException("Função não encontrada com o ID: " + dto.getFuncaoId()));

        Subfuncao sf = new Subfuncao();
        sf.setCodigoSubfuncao(dto.getCodigoSubfuncao());
        sf.setDescricao(dto.getDescricao());
        sf.setFuncao(funcao);

        return toDTO(subfuncaoRepository.save(sf));
    }

    @Transactional
    public SubfuncaoDTO atualizar(Long id, SubfuncaoDTO dto) {
        Subfuncao sf = subfuncaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subfunção não encontrada com o ID: " + id));
        Funcao funcao = funcaoRepository.findById(dto.getFuncaoId())
                .orElseThrow(() -> new EntityNotFoundException("Função não encontrada com o ID: " + dto.getFuncaoId()));

        sf.setCodigoSubfuncao(dto.getCodigoSubfuncao());
        sf.setDescricao(dto.getDescricao());
        sf.setFuncao(funcao);

        return toDTO(subfuncaoRepository.save(sf));
    }

    @Transactional
    public void deletar(Long id) {
        if (!subfuncaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Subfunção não encontrada com o ID: " + id);
        }
        subfuncaoRepository.deleteById(id);
    }

    private SubfuncaoDTO toDTO(Subfuncao sf) {
        return SubfuncaoDTO.builder()
                .id(sf.getId())
                .codigoSubfuncao(sf.getCodigoSubfuncao())
                .descricao(sf.getDescricao())
                .funcaoId(sf.getFuncao() != null ? sf.getFuncao().getId() : null)
                .build();
    }
}