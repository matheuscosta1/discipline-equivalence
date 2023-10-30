package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommandChain;
import br.com.tcc.project.command.repositoy.mapper.DisciplineDocumentMapper;
import br.com.tcc.project.command.repositoy.model.OpenAIEquivalenceAnalysisDocument;
import br.com.tcc.project.controller.response.EquivalenceDisciplineResponse;
import br.com.tcc.project.domain.MenuEquivalence;
import br.com.tcc.project.domain.Status;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import br.com.tcc.project.response.AnaliseEquivalenciaDisciplineResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.factory.Mappers;

@GenerateCommandFactory
public class RegisterDisciplineEquivalence
    extends AbstractCommandChain<
        RegisterDisciplineEquivalence.Request, EquivalenceDisciplineResponse> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer idDisciplinaOrigem;
    private Integer idDisciplinaDestino;
  }
  private final DisciplineDocumentMapper mapper = Mappers.getMapper(DisciplineDocumentMapper.class);

  @Override
  public void execute() throws IllegalStateException {
    super.execute();

    AnaliseEquivalenciaDisciplineResponse originDisciplineResponse = mapper.mapForResumo(invoke(
            FindDisciplineById.class,
            FindDisciplineById.Request.builder()
                    .id(getParameter().getIdDisciplinaOrigem())
                    .build()));

    AnaliseEquivalenciaDisciplineResponse destinyDisciplineResponse = mapper.mapForResumo(invoke(
            FindDisciplineById.class,
            FindDisciplineById.Request.builder()
                    .id(getParameter().getIdDisciplinaDestino())
                    .build()));


    OpenAIEquivalenceAnalysisDocument openAIEquivalenceAnalysisDocuments = invoke(
            FindOpenAIEquivalenceAnalysisByOriginDisciplineAndDestinyDisciplineAndProcessedStatus.class,
            FindOpenAIEquivalenceAnalysisByOriginDisciplineAndDestinyDisciplineAndProcessedStatus.Request.builder()
                    .originDisciplineId(originDisciplineResponse.getId())
                    .destinyDisciplineId(destinyDisciplineResponse.getId())
                    .status(Status.PROCESSED.name())
                    .build());

    MenuEquivalence menuEquivalence =
        invoke(
            ValidateDisciplineMenuIsUnderMinimumParametersToGiveEquivalence.class,
            ValidateDisciplineMenuIsUnderMinimumParametersToGiveEquivalence.Request.builder()
                .originDiscipline(originDisciplineResponse)
                .destinyDiscipline(destinyDisciplineResponse)
                .build());

    Boolean isValidWorkLoad =
        invoke(
            ValidateDisciplineWorkLoadIsUnderMinimumParametersToGiveEquivalence.class,
            ValidateDisciplineWorkLoadIsUnderMinimumParametersToGiveEquivalence.Request.builder()
                .originDiscipline(originDisciplineResponse)
                .destinyDiscipline(destinyDisciplineResponse)
                .build());

    BigDecimal menuEquivalencePercentage =
        BigDecimal.valueOf(menuEquivalence.getEmentaEquivalente().size())
            .multiply(BigDecimal.valueOf(100))
            .divide(
                BigDecimal.valueOf(
                    menuEquivalence.getEmentaEquivalente().size()
                        + menuEquivalence.getEmentaNaoEquivalente().size()),
                2,
                RoundingMode.HALF_UP);

    EquivalenceDisciplineResponse equivalenceDisciplineResponse =
        EquivalenceDisciplineResponse.builder()
            .equivalenciaEmenta(menuEquivalence)

            .cargaHorariaValida(isValidWorkLoad)
            .disciplinaOrigem(originDisciplineResponse)
            .disciplinaDestino(destinyDisciplineResponse)
            .percentualEquivalencia(menuEquivalencePercentage)
            .ementaEquivalente(openAIEquivalenceAnalysisDocuments.getSemelhanca())
            .ementaNaoEquivalente(openAIEquivalenceAnalysisDocuments.getDiferenca())
            .consideracaoFinal(openAIEquivalenceAnalysisDocuments.getConsideracao())
            .build();

    setResult(equivalenceDisciplineResponse);
  }
}
