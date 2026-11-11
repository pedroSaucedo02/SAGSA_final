package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.EixoTecnologico;
import sapz.sagsa.models.dtos.EixoTecnologicoDTO;
import sapz.sagsa.repositories.EixoTecnologicoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EixoTecnologicoService {

    private final EixoTecnologicoRepository eixoRepository;

    @Transactional(readOnly = true)
    public List<EixoTecnologicoDTO> listarTodos() {
        return eixoRepository.findAll().stream()
                .map(eixo -> EixoTecnologicoDTO.builder()
                        .id(eixo.getId())
                        .nome(eixo.getNome())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EixoTecnologicoDTO buscarPorId(Long id) {
        EixoTecnologico eixo = eixoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Eixo Tecnológico não encontrado com o ID: " + id));
        return EixoTecnologicoDTO.builder().id(eixo.getId()).nome(eixo.getNome()).build();
    }

    @Transactional
    public EixoTecnologicoDTO criar(EixoTecnologicoDTO dto) {
        EixoTecnologico eixo = new EixoTecnologico();
        eixo.setNome(dto.getNome());
        EixoTecnologico salvo = eixoRepository.save(eixo);
        return EixoTecnologicoDTO.builder().id(salvo.getId()).nome(salvo.getNome()).build();
    }

    @Transactional
    public EixoTecnologicoDTO atualizar(Long id, EixoTecnologicoDTO dto) {
        EixoTecnologico eixo = eixoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Eixo Tecnológico não encontrado com o ID: " + id));
        eixo.setNome(dto.getNome());
        EixoTecnologico salvo = eixoRepository.save(eixo);
        return EixoTecnologicoDTO.builder().id(salvo.getId()).nome(salvo.getNome()).build();
    }

    @Transactional
    public void deletar(Long id) {
        if (!eixoRepository.existsById(id)) {
            throw new EntityNotFoundException("Eixo Tecnológico não encontrado com o ID: " + id);
        }
        eixoRepository.deleteById(id);
    }
}