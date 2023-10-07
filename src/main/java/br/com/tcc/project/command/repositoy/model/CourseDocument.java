package br.com.tcc.project.command.repositoy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Builder
@Table(name = "course")
@Entity
public class CourseDocument {

  @Id
  private Integer id;

  private String name;

  @ManyToOne
  @JoinColumn(name="collegeId")
  private CollegeDocument college;

  public CourseDocument() {
  }

  public CourseDocument(Integer id, String name, CollegeDocument college) {
    this.id = id;
    this.name = name;
    this.college = college;
  }
}
