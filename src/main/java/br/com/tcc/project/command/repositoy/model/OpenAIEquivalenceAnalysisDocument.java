package br.com.tcc.project.command.repositoy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Builder
@Entity
@Table(name = "analise_equivalencia_open_ai")
public class OpenAIEquivalenceAnalysisDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "disciplinaOrigemId")
  private DisciplineDocument disciplinaOrigem;

  @ManyToOne
  @JoinColumn(name = "disciplinaDestinoId")
  private DisciplineDocument disciplinaDestino;

  private String status;

  private String semelhanca;

  private String diferenca;

  public String consideracao;

  public LocalDateTime dataCriacao;

  public OpenAIEquivalenceAnalysisDocument() {
  }

  public OpenAIEquivalenceAnalysisDocument(Integer id, DisciplineDocument disciplinaOrigem, DisciplineDocument disciplinaDestino, String status, String semelhanca, String diferenca, String consideracao, LocalDateTime dataCriacao) {
    this.id = id;
    this.disciplinaOrigem = disciplinaOrigem;
    this.disciplinaDestino = disciplinaDestino;
    this.status = status;
    this.semelhanca = semelhanca;
    this.diferenca = diferenca;
    this.consideracao = consideracao;
    this.dataCriacao = dataCriacao;
  }
}
