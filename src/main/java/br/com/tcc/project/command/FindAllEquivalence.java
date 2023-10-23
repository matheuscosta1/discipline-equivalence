package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import br.com.tcc.project.response.EquivalenceResponse;
import br.com.tcc.project.response.ProfessorResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@GenerateCommandFactory
public class FindAllEquivalence
    extends AbstractCommand<FindAllEquivalence.Request, Page<EquivalenceResponse>> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer pagina;
    private Integer paginas;
    private String orderBy;
    private String direction;
    private String codigo;
  }
}
