package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.RegisterProfessor;
import br.com.tcc.project.command.RegisterUser;
import br.com.tcc.project.command.discipline.mapper.ProfessorMapper;
import br.com.tcc.project.command.discipline.mapper.UserMapper;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.ProfessorRepository;
import br.com.tcc.project.command.repositoy.UserRepository;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import br.com.tcc.project.command.repositoy.model.UserDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(RegisterUser.class)
public class RegisterUserReceiver
    extends AbstractReceiver<RegisterUser.Request, UserDocument> {

  @Autowired @Setter private UserMapper userMapper;

  @Autowired @Setter private UserRepository userRepository;

  @Override
  protected UserDocument doExecute(RegisterUser.Request parameter) {
    return userRepository.save(userMapper.map(parameter));
  }
}
