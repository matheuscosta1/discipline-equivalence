package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineModificationDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class RegisterDisciplineModification
    extends AbstractCommand<RegisterDisciplineModification.Request, DisciplineModificationDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer id;
    private DisciplineDocument disciplineDocument;
    private String status;
  }
}
