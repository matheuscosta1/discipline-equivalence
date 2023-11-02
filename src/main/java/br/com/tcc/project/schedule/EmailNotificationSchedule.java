package br.com.tcc.project.schedule;

import br.com.tcc.project.command.FindAllEquivalenceNotifications;
import br.com.tcc.project.command.FindAllPendingAnalysisExpirationNotificationsByDate;
import br.com.tcc.project.command.RegisterProfessorNotification;
import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import br.com.tcc.project.controller.mapper.RegisterProfessorAnalysisControllerMapper;
import br.com.tcc.project.domain.NotificationStatus;
import br.com.tcc.project.email.EmailService;
import br.com.tcc.project.gateway.CommandGateway;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class EmailNotificationSchedule {
  private final RegisterProfessorAnalysisControllerMapper mapper =
          Mappers.getMapper(RegisterProfessorAnalysisControllerMapper.class);
  int DAYS_FOR_NOTIFICATION = 7;

  @Autowired
  private EmailService emailService;

  @Autowired
  @Setter
  private CommandGateway commandGateway;

  @Scheduled(cron = "${discipline-equivalence.email-notification.cron-schedule}")
  protected void schedule() throws MessagingException {
    List<NotificationDocument> analysisNotification = commandGateway.invoke(FindAllPendingAnalysisExpirationNotificationsByDate.class, FindAllPendingAnalysisExpirationNotificationsByDate.Request.builder().maximumDate(Date.from(Instant.now())).build());

    for (NotificationDocument notificationDocument : analysisNotification) {
      emailService.sendProfessorNotificationForAnaliseExpirationHtml(notificationDocument);
      commandGateway.invoke(RegisterProfessorNotification.class, mapper.map(notificationDocument.getAnalisesDocument(), normalizeDate(notificationDocument.getDataMaxima()), NotificationStatus.SENT, notificationDocument.getId(), notificationDocument.getEmail()));
    }

    List<NotificationDocument> equivalenceNotification = commandGateway.invoke(FindAllEquivalenceNotifications.class, FindAllEquivalenceNotifications.Request.builder().status(NotificationStatus.SENT.name()).build());

    for (NotificationDocument equivalence : equivalenceNotification) {
      emailService.sendEquivalenceReportHtmlEmail(equivalence.getEquivalencia());
      commandGateway.invoke(RegisterProfessorNotification.class,
              mapper.map(
                      equivalence.getAnalisesDocument(),
                      equivalence.getEquivalencia(),
                      normalizeDate(equivalence.getDataMaxima()),
                      NotificationStatus.SENT,
                      equivalence.getId(),
                      equivalence.getEmail())
      );
    }
  }

  private Date normalizeDate(Date maximumDate) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(maximumDate);

    calendar.add(Calendar.DAY_OF_MONTH, +DAYS_FOR_NOTIFICATION);

    return calendar.getTime();
  }
}
