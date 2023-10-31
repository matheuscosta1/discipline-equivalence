package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindEquivalenceByEquivalenceAnalysisIdAndStatusAnalyzed;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.EquivalenceRepository;
import br.com.tcc.project.command.repositoy.model.EquivalenceDocument;
import br.com.tcc.project.domain.Status;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(FindEquivalenceByEquivalenceAnalysisIdAndStatusAnalyzed.class)
public class FindAllEquivalenceByEquivalenceAnalysisIdReceiver
    extends AbstractReceiver<FindEquivalenceByEquivalenceAnalysisIdAndStatusAnalyzed.Request, EquivalenceDocument> {

  @Autowired @Setter private EquivalenceRepository equivalenceRepository;

  @Override
  protected EquivalenceDocument doExecute(FindEquivalenceByEquivalenceAnalysisIdAndStatusAnalyzed.Request parameter) {

    return equivalenceRepository.findByAnalysisId(parameter.getAnalysisId(), Status.ANALYZED.name());
  }

}