package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@GenerateCommandFactory
public class FindAllPendingAnalysisExpirationNotificationsByDate
    extends AbstractCommand<FindAllPendingAnalysisExpirationNotificationsByDate.Request, List<NotificationDocument>> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Date maximumDate;
  }
}
