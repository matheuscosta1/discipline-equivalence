package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindProfessorById;
import br.com.tcc.project.command.FindUserByEmail;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.CollegeNotFoundException;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.ProfessorRepository;
import br.com.tcc.project.command.repositoy.UserRepository;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import br.com.tcc.project.command.repositoy.model.UserDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

@CommandReceiver(FindUserByEmail.class)
public class FindUserByEmailReceiver
    extends AbstractReceiver<FindUserByEmail.Request, UserDocument> {

  @Autowired @Setter private UserRepository userRepository;

  @Override
  protected UserDocument doExecute(FindUserByEmail.Request parameter) {
    return userRepository
            .findByEmail(parameter.getEmail());
  }
}
