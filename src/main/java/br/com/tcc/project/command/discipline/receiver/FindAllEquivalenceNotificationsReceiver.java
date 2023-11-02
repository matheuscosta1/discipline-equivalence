package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllEquivalenceNotifications;
import br.com.tcc.project.command.FindAllPendingAnalysisExpirationNotificationsByDate;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.NotificationRepository;
import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import br.com.tcc.project.domain.NotificationStatus;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CommandReceiver(FindAllEquivalenceNotifications.class)
public class FindAllEquivalenceNotificationsReceiver
    extends AbstractReceiver<FindAllEquivalenceNotifications.Request, List<NotificationDocument>> {

  @Autowired @Setter private NotificationRepository notificationRepository;

  @Override
  protected List<NotificationDocument> doExecute(FindAllEquivalenceNotifications.Request parameter) {

    return notificationRepository.findAllEquivalenceNotifications(parameter.getStatus());
  }

}
