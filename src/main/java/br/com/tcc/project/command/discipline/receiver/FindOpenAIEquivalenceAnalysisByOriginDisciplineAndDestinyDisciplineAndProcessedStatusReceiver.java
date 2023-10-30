package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllPendingOpenAIEquivalenceAnalysis;
import br.com.tcc.project.command.FindOpenAIEquivalenceAnalysisByOriginDisciplineAndDestinyDisciplineAndProcessedStatus;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.OpenAIEquivalenceAnalysisRepository;
import br.com.tcc.project.command.repositoy.model.OpenAIEquivalenceAnalysisDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(FindOpenAIEquivalenceAnalysisByOriginDisciplineAndDestinyDisciplineAndProcessedStatus.class)
public class FindOpenAIEquivalenceAnalysisByOriginDisciplineAndDestinyDisciplineAndProcessedStatusReceiver
    extends AbstractReceiver<FindOpenAIEquivalenceAnalysisByOriginDisciplineAndDestinyDisciplineAndProcessedStatus.Request, OpenAIEquivalenceAnalysisDocument> {

  @Autowired @Setter private OpenAIEquivalenceAnalysisRepository openAIEquivalenceAnalysisRepository;

  @Override
  protected OpenAIEquivalenceAnalysisDocument doExecute(FindOpenAIEquivalenceAnalysisByOriginDisciplineAndDestinyDisciplineAndProcessedStatus.Request parameter) {

    return openAIEquivalenceAnalysisRepository
            .findByDisciplinaOrigemIdAndDisciplinaDestinoIdAndStatus(
                    parameter.getOriginDisciplineId(),
                    parameter.getDestinyDisciplineId(),
                    parameter.getStatus()
            );
  }

}