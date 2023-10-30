package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllPendingNotificationsByDate;
import br.com.tcc.project.command.FindPendingNotificationByAnalysisId;
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

@CommandReceiver(FindPendingNotificationByAnalysisId.class)
public class FindPendingNotificationByAnalysisIdReceiver
    extends AbstractReceiver<FindPendingNotificationByAnalysisId.Request, NotificationDocument> {

  @Autowired @Setter private NotificationRepository notificationRepository;

  @Override
  protected NotificationDocument doExecute(FindPendingNotificationByAnalysisId.Request parameter) {

    return notificationRepository.findByAnaliseIdAndStatusPending(parameter.getAnaliseId(), NotificationStatus.PENDING.name());
  }
}