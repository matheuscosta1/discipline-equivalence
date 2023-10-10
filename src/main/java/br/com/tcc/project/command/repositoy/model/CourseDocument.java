package br.com.tcc.project.command.repositoy.model;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Table(name = "curso")
@Entity
public class CourseDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nome;

  @ManyToOne
  @JoinColumn(name = "faculdadeId")
  private CollegeDocument faculdade;

  public CourseDocument() {}

  public CourseDocument(Integer id, String nome, CollegeDocument faculdade) {
    this.id = id;
    this.nome = nome;
    this.faculdade = faculdade;
  }
}
