package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindDisciplineById;
import br.com.tcc.project.command.FindProfessorById;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.CollegeNotFoundException;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.DisciplineRepository;
import br.com.tcc.project.command.repositoy.ProfessorRepository;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

@CommandReceiver(FindProfessorById.class)
public class FindProfessorByIdReceiver
    extends AbstractReceiver<FindProfessorById.Request, ProfessorDocument> {

  @Autowired @Setter private ProfessorRepository professorRepository;

  @Override
  protected ProfessorDocument doExecute(FindProfessorById.Request parameter) {
    ProfessorDocument professorDocument = professorRepository.findById(parameter.getId()).orElseThrow(
            () ->
                    new CollegeNotFoundException(
                            MessageFormat.format(
                                    DisciplineEquivalenceErrors.DEE0004.message(), parameter.getId()),
                            DisciplineEquivalenceErrors.DEE0004.name(),
                            DisciplineEquivalenceErrors.DEE0004.group()));
    return professorDocument;
  }
}
