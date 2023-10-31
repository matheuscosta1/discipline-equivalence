package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.DisciplineModificationDocument;
import br.com.tcc.project.command.repositoy.model.OpenAIEquivalenceAnalysisDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@GenerateCommandFactory
public class FindAllPendingDisciplineModification
    extends AbstractCommand<FindAllPendingDisciplineModification.Request, List<DisciplineModificationDocument>> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private String status;
  }
}
