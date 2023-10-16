package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindUserByEmail;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.UserRepository;
import br.com.tcc.project.command.repositoy.model.UsuarioDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(FindUserByEmail.class)
public class FindUserByEmailReceiver
    extends AbstractReceiver<FindUserByEmail.Request, UsuarioDocument> {

  @Autowired @Setter private UserRepository userRepository;

  @Override
  protected UsuarioDocument doExecute(FindUserByEmail.Request parameter) {
    return userRepository
            .findByEmail(parameter.getEmail());
  }
}
