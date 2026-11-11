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
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + id));
        return toDTO(aluno);
    }

    @Transactional
    public AlunoDTO criar(AlunoDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setCpf(dto.getCpf());
        aluno.setEmail(dto.getEmail());
        aluno.setMatricula(dto.getMatricula());
        return toDTO(alunoRepository.save(aluno));
    }

    @Transactional
    public AlunoDTO atualizar(Long id, AlunoDTO dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + id));

        aluno.setNome(dto.getNome());
        aluno.setCpf(dto.getCpf());
        aluno.setEmail(dto.getEmail());
        aluno.setMatricula(dto.getMatricula());
        return toDTO(alunoRepository.save(aluno));
    }

    @Transactional
    public void deletar(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("Aluno não encontrado com ID: " + id);
        }
        alunoRepository.deleteById(id);
    }

    private AlunoDTO toDTO(Aluno a) {
        return AlunoDTO.builder()
                .id(a.getId())
                .nome(a.getNome())
                .cpf(a.getCpf())
                .email(a.getEmail())
                .matricula(a.getMatricula())
                .build();
    }
}