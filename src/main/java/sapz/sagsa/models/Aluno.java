package sapz.sagsa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alunos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    @NotBlank
    @Size(max = 100)
    private String nome;

    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    @NotBlank
    @Size(max = 14)
    private String cpf;

    @Column(name = "email", length = 150)
    @Email
    @Size(max = 150)
    private String email;

    @Column(name = "matricula", length = 50, nullable = false, unique = true)
    @NotBlank
    @Size(max = 50)
    private String matricula;
}
