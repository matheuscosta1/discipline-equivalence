package br.com.tcc.project.command.repositoy.model;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "disciplina")
public class DisciplineDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nome;

  @Column(length = 50, nullable = false)
  private String codigoOrigem;

  private String ementa;

  private String programa;

  @Column(length = 50, nullable = false)
  private String cargaHoraria;

  @ManyToOne
  @JoinColumn(name = "faculdadeId")
  private CollegeDocument faculdade;

  @ManyToOne
  @JoinColumn(name = "cursoId")
  private CourseDocument curso;

  public DisciplineDocument() {}

  public DisciplineDocument(Integer id, String nome, String codigoOrigem, String ementa, String programa, String cargaHoraria, CollegeDocument faculdade, CourseDocument curso) {
    this.id = id;
    this.nome = nome;
    this.codigoOrigem = codigoOrigem;
    this.ementa = ementa;
    this.programa = programa;
    this.cargaHoraria = cargaHoraria;
    this.faculdade = faculdade;
    this.curso = curso;
  }
}
