package sapz.sagsa.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapz.sagsa.models.Aluno;
import sapz.sagsa.models.dtos.AlunoDTO;
import sapz.sagsa.repositories.AlunoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Transactional(readOnly = true)
    public List<AlunoDTO> listarTodos() {
        return alunoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AlunoDTO buscarPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o ID: " + id));
        return toDTO(aluno);
    }

    @Transactional
    public AlunoDTO criar(AlunoDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setMatricula(dto.getMatricula());
        aluno.setEmail(dto.getEmail());

        return toDTO(alunoRepository.save(aluno));
    }

    @Transactional
    public AlunoDTO atualizar(Long id, AlunoDTO dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o ID: " + id));

        aluno.setNome(dto.getNome());
        aluno.setMatricula(dto.getMatricula());
        aluno.setEmail(dto.getEmail());

        return toDTO(alunoRepository.save(aluno));
    }

    @Transactional
    public void deletar(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("Aluno não encontrado com o ID: " + id);
        }
        alunoRepository.deleteById(id);
    }

    private AlunoDTO toDTO(Aluno aluno) {
        return AlunoDTO.builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .matricula(aluno.getMatricula())
                .email(aluno.getEmail())
                .build();
    }
}