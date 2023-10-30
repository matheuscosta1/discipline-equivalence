package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllPendingOpenAIEquivalenceAnalysis;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.OpenAIEquivalenceAnalysisRepository;
import br.com.tcc.project.command.repositoy.model.OpenAIEquivalenceAnalysisDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CommandReceiver(FindAllPendingOpenAIEquivalenceAnalysis.class)
public class FindAllPendingOpenAIEquivalenceAnalysisReceiver
    extends AbstractReceiver<FindAllPendingOpenAIEquivalenceAnalysis.Request, List<OpenAIEquivalenceAnalysisDocument>> {

  @Autowired @Setter private OpenAIEquivalenceAnalysisRepository openAIEquivalenceAnalysisRepository;

  @Override
  protected List<OpenAIEquivalenceAnalysisDocument> doExecute(FindAllPendingOpenAIEquivalenceAnalysis.Request parameter) {

    return openAIEquivalenceAnalysisRepository
            .findAllOpenAIEquivalenceAnalysisByStatusNotProcessed(parameter.getStatus());
  }

}