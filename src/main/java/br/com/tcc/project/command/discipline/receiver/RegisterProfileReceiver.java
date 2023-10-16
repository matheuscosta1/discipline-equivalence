package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.RegisterProfile;
import br.com.tcc.project.command.discipline.mapper.ProfileMapper;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.ProfileRepository;
import br.com.tcc.project.command.repositoy.model.ProfileDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(RegisterProfile.class)
public class RegisterProfileReceiver
    extends AbstractReceiver<RegisterProfile.Request, ProfileDocument> {

  @Autowired @Setter private ProfileMapper userMapper;

  @Autowired @Setter private ProfileRepository profileRepository;

  @Override
  protected ProfileDocument doExecute(RegisterProfile.Request parameter) {
    return profileRepository.save(userMapper.map(parameter));
  }
}
