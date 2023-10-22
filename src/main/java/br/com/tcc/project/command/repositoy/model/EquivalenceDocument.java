package br.com.tcc.project.command.repositoy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Builder
@Entity
@Table(name = "equivalencia")
public class EquivalenceDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String justificativa;

  public Boolean equivalente;

  public Date dataCriacao;

  @OneToOne
  @JoinColumn(name = "analiseEquivalenciaId")
  private AnalisesDocument analisesDocument;

  public EquivalenceDocument() {}

  public EquivalenceDocument(Integer id, String justificativa, Boolean equivalente, Date dataCriacao, AnalisesDocument analisesDocument) {
    this.id = id;
    this.justificativa = justificativa;
    this.equivalente = equivalente;
    this.dataCriacao = dataCriacao;
    this.analisesDocument = analisesDocument;
  }
}
