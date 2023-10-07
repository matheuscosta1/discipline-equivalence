package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommandChain;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.controller.response.EquivalenceDisciplineResponse;
import br.com.tcc.project.domain.MenuEquivalence;
import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.tcc.project.domain.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class RegisterDisciplineEquivalence
    extends AbstractCommandChain<
            RegisterDisciplineEquivalence.Request, EquivalenceDisciplineResponse> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private String originCode;
    private String destinyCode;
  }

  @Override
  public void execute() throws IllegalStateException {
    super.execute();

    DisciplineDocument originDiscipline =
        invoke(
            FindDisciplineByCode.class,
            FindDisciplineByCode.Request.builder()
                .disciplineCode(getParameter().getOriginCode())
                .build());

    DisciplineDocument destinyDiscipline =
        invoke(
            FindDisciplineByCode.class,
            FindDisciplineByCode.Request.builder()
                .disciplineCode(getParameter().getDestinyCode())
                .build());

    MenuEquivalence menuEquivalence =
        invoke(
            ValidateDisciplineMenuIsUnderMinimumParametersToGiveEquivalence.class,
            ValidateDisciplineMenuIsUnderMinimumParametersToGiveEquivalence.Request.builder()
                .originDiscipline(originDiscipline)
                .destinyDiscipline(destinyDiscipline)
                .build());

    Boolean isValidWorkLoad =
        invoke(
            ValidateDisciplineWorkLoadIsUnderMinimumParametersToGiveEquivalence.class,
            ValidateDisciplineWorkLoadIsUnderMinimumParametersToGiveEquivalence.Request.builder()
                .originDiscipline(originDiscipline)
                .destinyDiscipline(destinyDiscipline)
                .build());

    BigDecimal menuEquivalencePercentage =
            BigDecimal.valueOf(menuEquivalence.getEquivalenceMenu().size())
                    .multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(menuEquivalence.getEquivalenceMenu().size() + menuEquivalence.getNonEquivalenceMenu().size()), 2, RoundingMode.HALF_UP);

    EquivalenceDisciplineResponse equivalenceDisciplineResponse =
        EquivalenceDisciplineResponse.builder()
            .menuEquivalence(menuEquivalence)
            .isValidWorkLoad(isValidWorkLoad)
            .originCollegeDiscipline(originDiscipline)
            .destinyCollegeDiscipline(destinyDiscipline)
            .menuEquivalencePercentage(menuEquivalencePercentage)
            .finalConsideration(isValidWorkLoad && menuEquivalencePercentage.compareTo(BigDecimal.valueOf(70.0)) >= 0 ? Status.EQUIVALENCE : Status.NON_EQUIVALENCE)
            .build();

    setResult(equivalenceDisciplineResponse);
  }
}
