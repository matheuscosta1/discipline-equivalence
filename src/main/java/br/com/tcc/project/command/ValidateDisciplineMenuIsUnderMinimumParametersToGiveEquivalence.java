package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.domain.MenuEquivalence;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import br.com.tcc.project.response.DisciplineResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class ValidateDisciplineMenuIsUnderMinimumParametersToGiveEquivalence
    extends AbstractCommand<
        ValidateDisciplineMenuIsUnderMinimumParametersToGiveEquivalence.Request, MenuEquivalence> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private DisciplineResponse originDiscipline;
    private DisciplineResponse destinyDiscipline;
  }
}
