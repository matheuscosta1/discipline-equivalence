package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.RegisterProfessorNotification;
import br.com.tcc.project.command.UpdateUserPassword;
import br.com.tcc.project.command.discipline.mapper.NotificationMapper;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.NotificationRepository;
import br.com.tcc.project.command.repositoy.UserRepository;
import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import br.com.tcc.project.command.repositoy.model.UserDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(UpdateUserPassword.class)
public class UpdateUserPasswordReceiver
    extends AbstractReceiver<UpdateUserPassword.Request, UserDocument> {

  @Autowired @Setter private UserRepository userRepository;


  @Override
  protected UserDocument doExecute(UpdateUserPassword.Request parameter) {
    return userRepository.save(parameter.getUserDocument());
  }
}
