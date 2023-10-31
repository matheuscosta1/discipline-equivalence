package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import br.com.tcc.project.command.repositoy.model.OpenAIEquivalenceAnalysisDocument;
import br.com.tcc.project.domain.NotificationStatus;
import br.com.tcc.project.domain.Status;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@GenerateCommandFactory
public class RegisterOpenAIEquivalenceAnalysis
    extends AbstractCommand<RegisterOpenAIEquivalenceAnalysis.Request, OpenAIEquivalenceAnalysisDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    Integer id;
    private DisciplineDocument originDisciplineDocument;
    private DisciplineDocument destinyDisciplineDocument;
    public Status status;
    public String resemblance;
    public String difference;
    public String consideration;
    public LocalDateTime createdAt;
  }
}
