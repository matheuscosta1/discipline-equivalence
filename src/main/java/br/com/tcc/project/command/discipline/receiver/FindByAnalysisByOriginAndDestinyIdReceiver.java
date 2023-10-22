package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindByAnalysisByOriginAndDestinyId;
import br.com.tcc.project.command.FindCollegeById;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.CollegeNotFoundException;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.CollegeRepository;
import br.com.tcc.project.command.repositoy.ProfessorAnalysisRepository;
import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

@CommandReceiver(FindByAnalysisByOriginAndDestinyId.class)
public class FindByAnalysisByOriginAndDestinyIdReceiver
    extends AbstractReceiver<FindByAnalysisByOriginAndDestinyId.Request, AnalisesDocument> {

  @Autowired @Setter private ProfessorAnalysisRepository professorAnalysisRepository;

  @Override
  protected AnalisesDocument doExecute(FindByAnalysisByOriginAndDestinyId.Request parameter) {

    return professorAnalysisRepository
        .findByFaculdadeOrigemIdAndFaculdadeDestinoIdAndDisciplinaOrigemIdAndDisciplinaDestinoIdAndStatus(parameter.getCollegeOriginId(), parameter.getCollegeDestinyId(), parameter.getDisciplineOriginId(), parameter.getDisciplineDestinyId(), parameter.getStatus());
  }
}
