package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllPendingDisciplineModification;
import br.com.tcc.project.command.FindAllPendingOpenAIEquivalenceAnalysis;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.DisciplineModificationRepository;
import br.com.tcc.project.command.repositoy.OpenAIEquivalenceAnalysisRepository;
import br.com.tcc.project.command.repositoy.model.DisciplineModificationDocument;
import br.com.tcc.project.command.repositoy.model.OpenAIEquivalenceAnalysisDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CommandReceiver(FindAllPendingDisciplineModification.class)
public class FindAllPendingDisciplineModificationReceiver
    extends AbstractReceiver<FindAllPendingDisciplineModification.Request, List<DisciplineModificationDocument>> {

  @Autowired @Setter private DisciplineModificationRepository disciplineModificationRepository;

  @Override
  protected List<DisciplineModificationDocument> doExecute(FindAllPendingDisciplineModification.Request parameter) {

    return disciplineModificationRepository
            .findAllDisciplineModificationByStatusNotProcessed(parameter.getStatus());
  }

}