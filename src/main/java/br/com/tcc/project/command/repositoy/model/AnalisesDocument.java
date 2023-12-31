package br.com.tcc.project.command.repositoy.model;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
@Entity
@Table(name = "analises")
public class AnalisesDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "faculdadeOrigemId")
  private CollegeDocument faculdadeOrigem;

  @ManyToOne
  @JoinColumn(name = "cursoOrigemId")
  private CourseDocument cursoOrigem;

  @ManyToOne
  @JoinColumn(name = "disciplinaOrigemId")
  private DisciplineDocument disciplinaOrigem;

  @ManyToOne
  @JoinColumn(name = "faculdadeDestinoId")
  private CollegeDocument faculdadeDestino;

  @ManyToOne
  @JoinColumn(name = "cursoDestinoId")
  private CourseDocument cursoDestino;

  @ManyToOne
  @JoinColumn(name = "disciplinaDestinoId")
  private DisciplineDocument disciplinaDestino;

  @ManyToOne
  @JoinColumn(name = "professorId")
  private ProfessorDocument professor;

  @ManyToOne
  @JoinColumn(name = "adminUserId")
  private UsuarioDocument usuarioAdmin;

  public Date dataMaxima;

  public String status;
  public String emailAluno;
  public String nomeAluno;

  public AnalisesDocument() {}

  public AnalisesDocument(Integer id, CollegeDocument faculdadeOrigem, CourseDocument cursoOrigem, DisciplineDocument disciplinaOrigem, CollegeDocument faculdadeDestino, CourseDocument cursoDestino, DisciplineDocument disciplinaDestino, ProfessorDocument professor, UsuarioDocument usuarioAdmin, Date dataMaxima, String status, String emailAluno, String nomeAluno) {
    this.id = id;
    this.faculdadeOrigem = faculdadeOrigem;
    this.cursoOrigem = cursoOrigem;
    this.disciplinaOrigem = disciplinaOrigem;
    this.faculdadeDestino = faculdadeDestino;
    this.cursoDestino = cursoDestino;
    this.disciplinaDestino = disciplinaDestino;
    this.professor = professor;
    this.usuarioAdmin = usuarioAdmin;
    this.dataMaxima = dataMaxima;
    this.status = status;
    this.emailAluno = emailAluno;
    this.nomeAluno = nomeAluno;
  }
}
