package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class FindNotificationByAnalysisId
    extends AbstractCommand<FindNotificationByAnalysisId.Request, NotificationDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer analiseId;
  }
}
