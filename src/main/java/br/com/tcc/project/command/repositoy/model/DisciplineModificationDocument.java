package br.com.tcc.project.command.repositoy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Builder
@Entity
@Table(name = "modificacao_ementa_disciplina")
public class DisciplineModificationDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String status;

  @ManyToOne
  @JoinColumn(name = "disciplinaId")
  private DisciplineDocument disciplina;

  public DisciplineModificationDocument() {}

  public DisciplineModificationDocument(Integer id, String status, DisciplineDocument disciplina) {
    this.id = id;
    this.status = status;
    this.disciplina = disciplina;
  }
}
