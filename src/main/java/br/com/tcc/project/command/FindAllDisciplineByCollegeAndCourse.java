package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import java.util.List;

import br.com.tcc.project.response.DisciplineResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@GenerateCommandFactory
public class FindAllDisciplineByCollegeAndCourse
    extends AbstractCommand<FindAllDisciplineByCollegeAndCourse.Request, Page<DisciplineResponse>> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer pagina;
    private Integer paginas;
    private String orderBy;
    private String direction;
    private String nome;
    private String faculdadeId;
    private String cursoId;
  }
}
