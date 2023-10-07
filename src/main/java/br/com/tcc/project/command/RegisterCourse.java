package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class RegisterCourse extends AbstractCommand<RegisterCourse.Request, Void> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private String name;
    private CollegeDocument collegeDocument;
  }
}
