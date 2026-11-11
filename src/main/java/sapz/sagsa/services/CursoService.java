package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.AreaTecnologica;
import sapz.sagsa.models.Curso;
import sapz.sagsa.models.dtos.CursoDTO;
import sapz.sagsa.repositories.AreaTecnologicaRepository;
import sapz.sagsa.repositories.CursoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final AreaTecnologicaRepository areaTecnologicaRepository;

    @Transactional(readOnly = true)
    public List<CursoDTO> listarTodos() {
        return cursoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CursoDTO buscarPorId(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com ID: " + id));
        return toDTO(curso);
    }

    @Transactional
    public CursoDTO criar(CursoDTO dto) {
        Curso curso = new Curso();
        curso.setNome(dto.getNome());
        curso.setTipoCurso(dto.getTipoCurso());
        if (dto.getAtivo() != null) curso.setAtivo(dto.getAtivo());

        if (dto.getAreaTecnologicaId() != null) {
            AreaTecnologica area = areaTecnologicaRepository.findById(dto.getAreaTecnologicaId())
                    .orElseThrow(() -> new EntityNotFoundException("Área Tecnológica não encontrada com ID: " + dto.getAreaTecnologicaId()));
            curso.setAreaTecnologica(area);
        }

        return toDTO(cursoRepository.save(curso));
    }

    @Transactional
    public CursoDTO atualizar(Long id, CursoDTO dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com ID: " + id));

        curso.setNome(dto.getNome());
        curso.setTipoCurso(dto.getTipoCurso());
        if (dto.getAtivo() != null) curso.setAtivo(dto.getAtivo());

        if (dto.getAreaTecnologicaId() != null) {
            AreaTecnologica area = areaTecnologicaRepository.findById(dto.getAreaTecnologicaId())
                    .orElseThrow(() -> new EntityNotFoundException("Área Tecnológica não encontrada com ID: " + dto.getAreaTecnologicaId()));
            curso.setAreaTecnologica(area);
        } else {
            curso.setAreaTecnologica(null);
        }

        return toDTO(cursoRepository.save(curso));
    }

    @Transactional
    public void deletar(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new EntityNotFoundException("Curso não encontrado com ID: " + id);
        }
        cursoRepository.deleteById(id);
    }

    private CursoDTO toDTO(Curso c) {
        return CursoDTO.builder()
                .id(c.getId())
                .nome(c.getNome())
                .tipoCurso(c.getTipoCurso())
                .ativo(c.getAtivo())
                .areaTecnologicaId(c.getAreaTecnologica() != null ? c.getAreaTecnologica().getId() : null)
                .areaTecnologicaNome(c.getAreaTecnologica() != null ? c.getAreaTecnologica().getNome() : null)
                .build();
    }
}