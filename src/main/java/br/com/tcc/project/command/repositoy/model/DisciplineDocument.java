package br.com.tcc.project.command.repositoy.model;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "discipline")
public class DisciplineDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @Column(name = "origin_code", length = 50, nullable = false)
  private String originCode;

  private String menu;

  private String program;

  @Column(name = "work_load", length = 50, nullable = false)
  private String workLoad;

  @ManyToOne
  @JoinColumn(name = "collegeId")
  private CollegeDocument college;

  @ManyToOne
  @JoinColumn(name = "courseId")
  private CourseDocument course;

  public DisciplineDocument() {}

  public DisciplineDocument(Integer id, String name, String originCode, String menu, String program, String workLoad, CollegeDocument college, CourseDocument course) {
    this.id = id;
    this.name = name;
    this.originCode = originCode;
    this.menu = menu;
    this.program = program;
    this.workLoad = workLoad;
    this.college = college;
    this.course = course;
  }
}
