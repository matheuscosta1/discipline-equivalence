package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.UpdateUserPassword;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.UserRepository;
import br.com.tcc.project.command.repositoy.model.UsuarioDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(UpdateUserPassword.class)
public class UpdateUserPasswordReceiver
    extends AbstractReceiver<UpdateUserPassword.Request, UsuarioDocument> {

  @Autowired @Setter private UserRepository userRepository;


  @Override
  protected UsuarioDocument doExecute(UpdateUserPassword.Request parameter) {
    return userRepository.save(parameter.getUsuarioDocument());
  }
}
