package br.com.tcc.project.schedule;

import br.com.tcc.project.command.*;
import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineModificationDocument;
import br.com.tcc.project.command.repositoy.model.EquivalenceDocument;
import br.com.tcc.project.controller.mapper.RegisterProfessorAnalysisControllerMapper;
import br.com.tcc.project.domain.Status;
import br.com.tcc.project.gateway.CommandGateway;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

@Component
public class DisciplineModificationSchedule {
  private final RegisterProfessorAnalysisControllerMapper mapper =
          Mappers.getMapper(RegisterProfessorAnalysisControllerMapper.class);

  @Autowired
  @Setter
  private CommandGateway commandGateway;
  private final RegisterProfessorAnalysisControllerMapper professorAnalysisControllerMapper =
          Mappers.getMapper(RegisterProfessorAnalysisControllerMapper.class);
  @Scheduled(cron = "${discipline-equivalence.discipline-modification.cron-schedule}")
  protected void schedule() throws ParseException {
    List<DisciplineModificationDocument> disciplineModificationDocuments = commandGateway.invoke(FindAllPendingDisciplineModification.class,
            FindAllPendingDisciplineModification
                    .Request
                    .builder()
                    .status(Status.PROCESSED.name())
                    .build());

    for (DisciplineModificationDocument disciplineModificationDocument : disciplineModificationDocuments) {

      List<AnalisesDocument> analisesDocuments = commandGateway.invoke(
              FindAllAnalysisByDisciplineId.class,
              FindAllAnalysisByDisciplineId.Request.builder().disciplineId(disciplineModificationDocument.getDisciplina().getId()).build()
      );

      for (AnalisesDocument analisesDocument : analisesDocuments) {
        if(Status.ANALYZED.name().equals(analisesDocument.getStatus())) {
          EquivalenceDocument equivalenceDocument = commandGateway.invoke(FindEquivalenceByEquivalenceAnalysisIdAndStatusAnalyzed.class,
                  FindEquivalenceByEquivalenceAnalysisIdAndStatusAnalyzed.Request.builder().analysisId(analisesDocument.getId()).build());

          if(equivalenceDocument != null) {
            commandGateway.invoke(
                    RegisterEquivalence.class,
                    RegisterEquivalence.Request.builder()
                            .id(equivalenceDocument.getId())
                            .analisesDocument(equivalenceDocument.getAnalisesDocument())
                            .equivalent(equivalenceDocument.getEquivalente())
                            .justification(equivalenceDocument.getJustificativa())
                            .status(Status.MENU_CHANGE.name())
                            .build());
          }

          commandGateway.invoke(
                  RegisterProfessorAnalysis.class,
                  professorAnalysisControllerMapper.map(
                          analisesDocument.getDataMaxima(),
                          analisesDocument.getFaculdadeOrigem(),
                          analisesDocument.getCursoOrigem(),
                          analisesDocument.getDisciplinaOrigem(),
                          analisesDocument.getFaculdadeDestino(),
                          analisesDocument.getCursoDestino(),
                          analisesDocument.getDisciplinaDestino(),
                          analisesDocument.getProfessor(),
                          analisesDocument.getUsuarioAdmin(),
                          analisesDocument.getId(),
                          analisesDocument.getEmailAluno(),
                          analisesDocument.getNomeAluno(),
                          Status.MENU_CHANGE.name()));
        }
      }

      commandGateway.invoke(RegisterDisciplineModification.class,
              RegisterDisciplineModification
                      .Request
                      .builder()
                      .id(disciplineModificationDocument.getId())
                      .disciplineDocument(disciplineModificationDocument.getDisciplina())
                      .status(Status.PROCESSED.name())
                      .build()
      );
    }
  }
}
