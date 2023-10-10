package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import br.com.tcc.project.response.CourseResponse;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class FindAllCourseByCollege
    extends AbstractCommand<FindAllCourseByCollege.Request, List<CourseResponse>> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer pagina;
    private Integer paginas;
    private String orderBy;
    private String direction;
    private Integer faculdadeId;
  }
}
