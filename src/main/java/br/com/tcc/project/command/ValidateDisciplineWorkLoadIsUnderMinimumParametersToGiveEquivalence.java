package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import br.com.tcc.project.response.AnaliseEquivalenciaDisciplineResponse;
import br.com.tcc.project.response.DisciplineResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class ValidateDisciplineWorkLoadIsUnderMinimumParametersToGiveEquivalence
    extends AbstractCommand<
        ValidateDisciplineWorkLoadIsUnderMinimumParametersToGiveEquivalence.Request, Boolean> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private AnaliseEquivalenciaDisciplineResponse originDiscipline;
    private AnaliseEquivalenciaDisciplineResponse destinyDiscipline;
  }
}
