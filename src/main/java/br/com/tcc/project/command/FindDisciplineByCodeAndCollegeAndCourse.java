package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import br.com.tcc.project.response.DisciplineResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@GenerateCommandFactory
public class FindDisciplineByCodeAndCollegeAndCourse
    extends AbstractCommand<FindDisciplineByCodeAndCollegeAndCourse.Request, DisciplineDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private String codigo;
    private Integer faculdadeId;
    private Integer cursoId;
  }
}
