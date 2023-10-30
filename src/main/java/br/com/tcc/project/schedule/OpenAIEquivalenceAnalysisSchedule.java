package br.com.tcc.project.schedule;

import br.com.tcc.project.command.*;
import br.com.tcc.project.command.repositoy.model.NotificationDocument;
import br.com.tcc.project.command.repositoy.model.OpenAIEquivalenceAnalysisDocument;
import br.com.tcc.project.controller.mapper.RegisterProfessorAnalysisControllerMapper;
import br.com.tcc.project.domain.NotificationStatus;
import br.com.tcc.project.domain.OpenAIEquivalenceAnalysisResponse;
import br.com.tcc.project.domain.Status;
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
public class OpenAIEquivalenceAnalysisSchedule {
  private final RegisterProfessorAnalysisControllerMapper mapper =
          Mappers.getMapper(RegisterProfessorAnalysisControllerMapper.class);

  @Autowired
  @Setter
  private CommandGateway commandGateway;

  @Scheduled(cron = "${discipline-equivalence.open-ai-analysis.cron-schedule}")
  protected void schedule() {
    List<OpenAIEquivalenceAnalysisDocument> openAIEquivalenceAnalysisDocuments = commandGateway.invoke(FindAllPendingOpenAIEquivalenceAnalysis.class,
            FindAllPendingOpenAIEquivalenceAnalysis
                    .Request
                    .builder()
                    .status(Status.PROCESSED.name())
                    .build());

    for (OpenAIEquivalenceAnalysisDocument openAIEquivalenceAnalysisDocument : openAIEquivalenceAnalysisDocuments) {

      OpenAIEquivalenceAnalysisResponse openAIEquivalenceAnalysisResponse = commandGateway.invoke(
              OpenAIEquivalenceAnalysis.class,
              OpenAIEquivalenceAnalysis
                      .Request
                      .builder()
                      .disciplinaOrigem(openAIEquivalenceAnalysisDocument.getDisciplinaOrigem())
                      .disciplinaDestino(openAIEquivalenceAnalysisDocument.getDisciplinaDestino())
                      .build()
      );

      commandGateway.invoke(RegisterOpenAIEquivalenceAnalysis.class,
              RegisterOpenAIEquivalenceAnalysis
                      .Request
                      .builder()
                      .id(openAIEquivalenceAnalysisDocument.getId())
                      .destinyDisciplineDocument(openAIEquivalenceAnalysisDocument.getDisciplinaDestino())
                      .originDisciplineDocument(openAIEquivalenceAnalysisDocument.getDisciplinaOrigem())
                      .difference(openAIEquivalenceAnalysisResponse.difference)
                      .resemblance(openAIEquivalenceAnalysisResponse.resemblance)
                      .consideration(openAIEquivalenceAnalysisResponse.consideration)
                      .status(Status.PROCESSED)
                      .build()
      );
    }
  }

}
