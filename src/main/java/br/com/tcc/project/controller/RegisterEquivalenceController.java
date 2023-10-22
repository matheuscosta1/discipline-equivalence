package br.com.tcc.project.controller;

import br.com.tcc.project.command.*;
import br.com.tcc.project.command.repositoy.mapper.EquivalenceDocumentMapper;
import br.com.tcc.project.command.repositoy.mapper.ProfessorDocumentMapper;
import br.com.tcc.project.command.repositoy.model.*;
import br.com.tcc.project.controller.mapper.RegisterEquivalenceControllerMapper;
import br.com.tcc.project.controller.mapper.RegisterProfessorAnalysisControllerMapper;
import br.com.tcc.project.controller.mapper.RegisterProfessorControllerMapper;
import br.com.tcc.project.controller.request.RegisterEquivalenceRequest;
import br.com.tcc.project.controller.request.RegisterProfessorAnalysisRequest;
import br.com.tcc.project.controller.request.RegisterProfessorRequest;
import br.com.tcc.project.domain.Profile;
import br.com.tcc.project.domain.Status;
import br.com.tcc.project.exception.documentation.DocApiResponsesError;
import br.com.tcc.project.gateway.CommandGateway;
import br.com.tcc.project.response.EquivalenceResponse;
import br.com.tcc.project.response.ProfessorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Random;

@Tag(name = "Professor")
@RestController
@Slf4j
@Validated
public class RegisterEquivalenceController {

  @Autowired @Setter private CommandGateway commandGateway;
  private final RegisterEquivalenceControllerMapper mapper =
      Mappers.getMapper(RegisterEquivalenceControllerMapper.class);
  private final EquivalenceDocumentMapper professorDocumentMapper =
      Mappers.getMapper(EquivalenceDocumentMapper.class);
  private final RegisterProfessorAnalysisControllerMapper professorAnalysisControllerMapper =
          Mappers.getMapper(RegisterProfessorAnalysisControllerMapper.class);

  @Operation(summary = "Register new equivalence", description = "Register new equivalence")
  @DocApiResponsesError
  @PostMapping("registro-equivalencia")
  @PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
  public ResponseEntity<EquivalenceResponse> registerEquivalence(
      @Valid @RequestBody RegisterEquivalenceRequest request) throws ParseException {


    AnalisesDocument analisesDocument = commandGateway.invoke(
            FindByAnalysisByOriginAndDestinyId.class,
            FindByAnalysisByOriginAndDestinyId.Request
                    .builder()
                    .collegeOriginId(request.getFaculdadeOrigemId())
                    .collegeDestinyId(request.getFaculdadeDestinoId())
                    .disciplineOriginId(request.getDisciplinaOrigemId())
                    .disciplineDestinyId(request.getDisciplinaDestinoId())
                    .status(Status.PENDING.name())
                    .build());

    EquivalenceDocument equivalenceDocument = commandGateway.invoke(
            RegisterEquivalence.class,
            mapper.map(request, analisesDocument, null));


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
                    analisesDocument.getId(),
                    Status.ANALYZED.name()));

    return ResponseEntity.ok(professorDocumentMapper.map(equivalenceDocument));
  }
}
