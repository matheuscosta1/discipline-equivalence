package br.com.tcc.project.command.repositoy.model;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "faculdade")
public class CollegeDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 50, nullable = false)
  private String nome;

  public CollegeDocument(Integer id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public CollegeDocument() {}
}
