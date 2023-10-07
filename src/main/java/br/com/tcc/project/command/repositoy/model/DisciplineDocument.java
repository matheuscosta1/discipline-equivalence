package br.com.tcc.project.command.repositoy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Builder
@Entity
@Table(name = "discipline")
public class DisciplineDocument {

  @Id
  private String id;

  private Integer workLoad;

  private String originCode;

  private String course;

  private String menu;

  private String college;

  private String name;

  private String program;
  public DisciplineDocument() {
  }
  public DisciplineDocument(String id, Integer workLoad, String originCode, String course, String menu, String college, String name, String program) {
    this.id = id;
    this.workLoad = workLoad;
    this.originCode = originCode;
    this.course = course;
    this.menu = menu;
    this.college = college;
    this.name = name;
    this.program = program;
  }
}
