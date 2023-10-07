package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import java.util.List;

import br.com.tcc.project.response.DisciplineResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class FindAllDisciplineByCollegeAndCourse
    extends AbstractCommand<FindAllDisciplineByCollegeAndCourse.Request, List<DisciplineResponse>> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer collegeId;
    private Integer courseId;
  }
}
