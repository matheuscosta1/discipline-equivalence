package br.com.tcc.project.command.repositoy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Builder
@Entity
@Table(name = "college")
public class CollegeDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length=50, nullable=false)
  private String name;

  public CollegeDocument(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public CollegeDocument() {
  }
}
