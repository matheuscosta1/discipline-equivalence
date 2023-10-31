package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAnalysisByOriginAndDestinyId;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.ProfessorAnalysisRepository;
import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.domain.Status;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(FindAnalysisByOriginAndDestinyId.class)
public class FindByAnalysisByOriginAndDestinyIdReceiver
    extends AbstractReceiver<FindAnalysisByOriginAndDestinyId.Request, AnalisesDocument> {

  @Autowired @Setter private ProfessorAnalysisRepository professorAnalysisRepository;

  @Override
  protected AnalisesDocument doExecute(FindAnalysisByOriginAndDestinyId.Request parameter) {

    return professorAnalysisRepository
        .findByFaculdadeOrigemIdAndFaculdadeDestinoIdAndDisciplinaOrigemIdAndDisciplinaDestinoIdAndStatus(parameter.getCollegeOriginId(), parameter.getCollegeDestinyId(), parameter.getDisciplineOriginId(), parameter.getDisciplineDestinyId(), parameter.getStatus());
  }
}
