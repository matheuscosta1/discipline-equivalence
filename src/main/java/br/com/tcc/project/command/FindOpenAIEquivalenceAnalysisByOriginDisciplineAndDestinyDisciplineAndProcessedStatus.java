package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.OpenAIEquivalenceAnalysisDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@GenerateCommandFactory
public class FindOpenAIEquivalenceAnalysisByOriginDisciplineAndDestinyDisciplineAndProcessedStatus
    extends AbstractCommand<FindOpenAIEquivalenceAnalysisByOriginDisciplineAndDestinyDisciplineAndProcessedStatus.Request, OpenAIEquivalenceAnalysisDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer originDisciplineId;
    private Integer destinyDisciplineId;
    private String status;
  }
}
