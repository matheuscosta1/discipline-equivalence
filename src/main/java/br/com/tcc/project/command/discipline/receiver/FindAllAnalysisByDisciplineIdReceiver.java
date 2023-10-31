package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllAnalysisByDisciplineId;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.ProfessorAnalysisRepository;
import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.domain.Status;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CommandReceiver(FindAllAnalysisByDisciplineId.class)
public class FindAllAnalysisByDisciplineIdReceiver
    extends AbstractReceiver<FindAllAnalysisByDisciplineId.Request, List<AnalisesDocument>> {

  @Autowired @Setter private ProfessorAnalysisRepository professorAnalysisRepository;

  @Override
  protected List<AnalisesDocument> doExecute(FindAllAnalysisByDisciplineId.Request parameter) {
    return professorAnalysisRepository.findAllByDisciplinaIdAndStatus(parameter.getDisciplineId(), Status.ANALYZED.name());
  }
}
