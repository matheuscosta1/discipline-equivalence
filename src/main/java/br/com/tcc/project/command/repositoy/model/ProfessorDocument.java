package br.com.tcc.project.command.repositoy.model;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "professor")
public class ProfessorDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nome;

  @OneToOne
  @JoinColumn(name = "usuarioId")
  private UsuarioDocument usuario;

  @ManyToOne
  @JoinColumn(name = "faculdadeId")
  private CollegeDocument faculdade;

  @ManyToOne
  @JoinColumn(name = "cursoId")
  private CourseDocument curso;

  @ManyToOne
  @JoinColumn(name = "disciplinaId")
  private DisciplineDocument disciplina;

  public ProfessorDocument() {}

  public ProfessorDocument(Integer id, String nome, UsuarioDocument usuario, CollegeDocument faculdade, CourseDocument curso, DisciplineDocument disciplina) {
    this.id = id;
    this.nome = nome;
    this.usuario = usuario;
    this.faculdade = faculdade;
    this.curso = curso;
    this.disciplina = disciplina;
  }
}
