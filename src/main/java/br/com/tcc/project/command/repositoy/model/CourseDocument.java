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

  private String name;

  @ManyToOne
  @JoinColumn(name = "collegeId")
  private CollegeDocument college;

  public CourseDocument() {}

  public CourseDocument(Integer id, String name, CollegeDocument college) {
    this.id = id;
    this.name = name;
    this.college = college;
  }
}
