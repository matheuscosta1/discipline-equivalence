package br.com.tcc.project.controller;

import br.com.tcc.project.command.*;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.EquivalenceAlreadyRegisteredException;
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
import br.com.tcc.project.email.EmailService;
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
import org.springframework.scheduling.concurrent.DefaultManagedAwareThreadFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.text.MessageFormat;
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

  @Autowired
  private EmailService emailService;

  @Operation(summary = "Register new equivalence", description = "Register new equivalence")
  @DocApiResponsesError
  @PostMapping("registro-equivalencia")
  @PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
  public ResponseEntity<EquivalenceResponse> registerEquivalence(
      @Valid @RequestBody RegisterEquivalenceRequest request) throws ParseException, MessagingException {


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

    if(analisesDocument == null) {
      log.error(DisciplineEquivalenceErrors.DEE0007.message());
      throw new EquivalenceAlreadyRegisteredException(
              DisciplineEquivalenceErrors.DEE0007.message(),
              DisciplineEquivalenceErrors.DEE0007.name(),
              DisciplineEquivalenceErrors.DEE0007.group());
    }

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
                    analisesDocument.getUsuarioAdmin(),
                    analisesDocument.getId(),
                    Status.ANALYZED.name()));


    emailService.sendOrderConfirmationHtmlEmail(equivalenceDocument);

    return ResponseEntity.ok(professorDocumentMapper.map(equivalenceDocument));
  }

  @Operation(
          summary = "Find discipline by college and course",
          description = "Find discipline by college and course")
  @DocApiResponsesError
  @GetMapping("equivalencias")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<Page<EquivalenceResponse>> findAllEquivalence(
          @RequestParam(value = "pagina", defaultValue = "0", required = false) Integer pagina,
          @RequestParam(value = "paginas", defaultValue = "25", required = false) Integer paginas,
          @RequestParam(value = "orderBy", defaultValue = "equivalente", required = false) String orderBy,
          @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction,
          @RequestParam(value = "codigo", required = false) String codigo) {

    Page<EquivalenceResponse> response = commandGateway.invoke(
            FindAllEquivalence.class,
            FindAllEquivalence.Request.builder()
                    .pagina(pagina)
                    .paginas(paginas)
                    .orderBy(orderBy)
                    .direction(direction)
                    .codigo(codigo)
                    .build());

    return ResponseEntity.ok(response);
  }
}
