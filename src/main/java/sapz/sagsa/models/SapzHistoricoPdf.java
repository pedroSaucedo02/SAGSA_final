package sapz.sagsa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sapz_historico_pdf")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SapzHistoricoPdf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "situacao_aprendizagem_id", nullable = false)
    @NotNull
    private SituacaoAprendizagem situacaoAprendizagem;

    @Column(name = "versao", length = 20, nullable = false)
    @NotBlank
    @Size(max = 20)
    private String versao = "1.0";

    @Column(name = "caminho_arquivo_pdf", length = 255, nullable = false)
    @NotBlank
    @Size(max = 255)
    private String caminhoArquivoPdf;

    @Column(name = "hash_conteudo", length = 64)
    @Size(max = 64)
    private String hashConteudo;

    @Column(name = "data_geracao", nullable = false, insertable = false, updatable = false)
    private LocalDateTime dataGeracao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "criado_por_usuario_id", nullable = false)
    @NotNull
    private Usuario criadoPorUsuario;
}
