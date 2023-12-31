package br.com.tcc.project.command.discipline.receiver;

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

@CommandReceiver(FindAllPendingAnalysisExpirationNotificationsByDate.class)
public class FindAllPendingNotificationsByDateReceiver
    extends AbstractReceiver<FindAllPendingAnalysisExpirationNotificationsByDate.Request, List<NotificationDocument>> {

  @Autowired @Setter private NotificationRepository notificationRepository;

  @Override
  protected List<NotificationDocument> doExecute(FindAllPendingAnalysisExpirationNotificationsByDate.Request parameter) {

    return notificationRepository.findByDataMaximaAndStatusIsNotSent(formatDate(parameter.getMaximumDate()), NotificationStatus.SENT.name());
  }

  private String formatDate(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(date);
  }
}
