package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.domain.Discipline;
import br.com.tcc.project.domain.MenuEquivalence;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import br.com.tcc.project.response.AnaliseEquivalenciaDisciplineResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class GetSummaryMenuInformationFromOpenAI
    extends AbstractCommand<
        GetSummaryMenuInformationFromOpenAI.Request, String> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private DisciplineDocument originDiscipline;
    private DisciplineDocument destinyDiscipline;
  }
}
