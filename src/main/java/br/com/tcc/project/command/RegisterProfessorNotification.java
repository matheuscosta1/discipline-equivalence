package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.*;
import br.com.tcc.project.domain.NotificationStatus;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@GenerateCommandFactory
public class RegisterProfessorNotification
    extends AbstractCommand<RegisterProfessorNotification.Request, NotificationDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    Integer id;
    String email;
    private AnalisesDocument analisesDocument;
    private Date maximumDate;
    public NotificationStatus status;
  }
}
