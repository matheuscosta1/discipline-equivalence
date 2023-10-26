package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.EquivalenceDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import br.com.tcc.project.response.EquivalenceResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@GenerateCommandFactory
public class FindEquivalenceByAnalysisId
    extends AbstractCommand<FindEquivalenceByAnalysisId.Request, EquivalenceDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer analysisId;
  }
}
