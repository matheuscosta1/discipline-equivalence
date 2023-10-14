package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.RegisterProfessorNotification;
import br.com.tcc.project.command.discipline.mapper.NotificationMapper;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.NotificationRepository;
import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import br.com.tcc.project.domain.NotificationStatus;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(RegisterProfessorNotification.class)
public class RegisterProfessorNotificationReceiver
    extends AbstractReceiver<RegisterProfessorNotification.Request, NotificationDocument> {

  @Autowired @Setter private NotificationMapper notificationMapper;

  @Autowired @Setter private NotificationRepository notificationRepository;

  @Override
  protected NotificationDocument doExecute(RegisterProfessorNotification.Request parameter) {
    return notificationRepository.save(notificationMapper.map(parameter));
  }
}
