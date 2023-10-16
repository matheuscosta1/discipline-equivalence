package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindProfessorById;
import br.com.tcc.project.command.FindProfessorByUserId;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.CollegeNotFoundException;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.ProfessorRepository;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

@CommandReceiver(FindProfessorByUserId.class)
public class FindProfessorByUserIdReceiver
    extends AbstractReceiver<FindProfessorByUserId.Request, ProfessorDocument> {

  @Autowired @Setter private ProfessorRepository professorRepository;

  @Override
  protected ProfessorDocument doExecute(FindProfessorByUserId.Request parameter) {
    return professorRepository
            .findByUsuarioId(parameter.getId());
  }
}
