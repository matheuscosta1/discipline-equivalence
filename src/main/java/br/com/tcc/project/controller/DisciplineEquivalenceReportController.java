package br.com.tcc.project.controller;

import br.com.tcc.project.command.RegisterDisciplineEquivalence;
import br.com.tcc.project.controller.request.RegisterDisciplineEquivalenceReportRequest;
import br.com.tcc.project.controller.response.EquivalenceDisciplineResponse;
import br.com.tcc.project.exception.documentation.DocApiResponsesError;
import br.com.tcc.project.gateway.CommandGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Register Discipline Equivalence")
@RestController
@Slf4j
@Validated
public class DisciplineEquivalenceReportController {

  @Autowired @Setter private CommandGateway commandGateway;

  @Operation(summary = "Register new discipline", description = "Register new discipline")
  @DocApiResponsesError
  @PostMapping("relatorio-equivalencia")
  @PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
  public ResponseEntity<EquivalenceDisciplineResponse> registerDisciplineEquivalence(
      @Valid @RequestBody RegisterDisciplineEquivalenceReportRequest request) {
    EquivalenceDisciplineResponse equivalenceDisciplineResponse =
        commandGateway.invoke(
            RegisterDisciplineEquivalence.class,
            RegisterDisciplineEquivalence.Request.builder()
                .idDisciplinaOrigem(request.getIdDisciplinaOrigem())
                .idDisciplinaDestino(request.getIdDisciplinaDestino())
                .build());

    return ResponseEntity.ok(equivalenceDisciplineResponse);
  }
}
