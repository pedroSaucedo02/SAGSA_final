package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.Funcao;
import sapz.sagsa.models.PlanoCurso;
import sapz.sagsa.models.dtos.FuncaoDTO;
import sapz.sagsa.repositories.FuncaoRepository;
import sapz.sagsa.repositories.PlanoCursoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncaoService {

    private final FuncaoRepository funcaoRepository;
    private final PlanoCursoRepository planoCursoRepository;

    @Transactional(readOnly = true)
    public List<FuncaoDTO> listarPorPlanoCursoId(Long planoId) {
        return funcaoRepository.findAll().stream()
                .filter(f -> f.getPlanoCurso() != null && f.getPlanoCurso().getId().equals(planoId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public FuncaoDTO criar(FuncaoDTO dto) {
        PlanoCurso plano = planoCursoRepository.findById(dto.getPlanoCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Plano de curso não encontrado com o ID: " + dto.getPlanoCursoId()));

        Funcao funcao = new Funcao();
        funcao.setNumero(dto.getNumero());
        funcao.setDescricao(dto.getDescricao());
        funcao.setPlanoCurso(plano);

        return toDTO(funcaoRepository.save(funcao));
    }

    @Transactional
    public FuncaoDTO atualizar(Long id, FuncaoDTO dto) {
        Funcao funcao = funcaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Função não encontrada com o ID: " + id));
        PlanoCurso plano = planoCursoRepository.findById(dto.getPlanoCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Plano de curso não encontrado com o ID: " + dto.getPlanoCursoId()));

        funcao.setNumero(dto.getNumero());
        funcao.setDescricao(dto.getDescricao());
        funcao.setPlanoCurso(plano);

        return toDTO(funcaoRepository.save(funcao));
    }

    @Transactional
    public void deletar(Long id) {
        if (!funcaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Função não encontrada com o ID: " + id);
        }
        funcaoRepository.deleteById(id);
    }

    private FuncaoDTO toDTO(Funcao f) {
        return FuncaoDTO.builder()
                .id(f.getId())
                .numero(f.getNumero())
                .descricao(f.getDescricao())
                .planoCursoId(f.getPlanoCurso() != null ? f.getPlanoCurso().getId() : null)
                .build();
    }
}