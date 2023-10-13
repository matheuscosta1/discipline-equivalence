package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class RegisterDiscipline
    extends AbstractCommand<RegisterDiscipline.Request, DisciplineDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer id;
    private Integer workLoad;
    private String originCode;
    private String menu;
    private String name;
    private String program;
    private CollegeDocument collegeDocument;
    private CourseDocument courseDocument;
  }
}
