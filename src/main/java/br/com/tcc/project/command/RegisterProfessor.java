package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class RegisterProfessor extends AbstractCommand<RegisterProfessor.Request, ProfessorDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private String nome;
    private CollegeDocument collegeDocument;
    private CourseDocument courseDocument;
    private DisciplineDocument disciplineDocument;
  }
}
