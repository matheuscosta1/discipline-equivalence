package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindProfessorAnalysisById;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.CollegeNotFoundException;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.ProfessorAnalysisRepository;
import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import java.text.MessageFormat;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(FindProfessorAnalysisById.class)
public class FindProfessorAnalysisByIdReceiver
    extends AbstractReceiver<FindProfessorAnalysisById.Request, AnalisesDocument> {

  @Autowired @Setter private ProfessorAnalysisRepository professorAnalysisRepository;

  @Override
  protected AnalisesDocument doExecute(FindProfessorAnalysisById.Request parameter) {
    AnalisesDocument analisesDocument =
        professorAnalysisRepository
            .findById(parameter.getId())
            .orElseThrow(
                () ->
                    new CollegeNotFoundException(
                        MessageFormat.format(
                            DisciplineEquivalenceErrors.DEE0004.message(), parameter.getId()),
                        DisciplineEquivalenceErrors.DEE0004.name(),
                        DisciplineEquivalenceErrors.DEE0004.group()));
    return analisesDocument;
  }
}
