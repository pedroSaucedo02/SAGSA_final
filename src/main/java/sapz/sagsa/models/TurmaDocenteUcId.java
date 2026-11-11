package sapz.sagsa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaDocenteUcId implements Serializable {
    private Long turma;
    private Long unidadeCurricular;
    private Long instrutor;
}
