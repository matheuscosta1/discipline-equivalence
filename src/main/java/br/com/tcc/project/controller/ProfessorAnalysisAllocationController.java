package br.com.tcc.project.controller;

import br.com.tcc.project.command.*;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.AnalysisAlreadyRegisteredException;
import br.com.tcc.project.command.exception.DuplicateEntryException;
import br.com.tcc.project.command.repositoy.mapper.ProfessorAnalysisDocumentMapper;
import br.com.tcc.project.command.repositoy.model.*;
import br.com.tcc.project.controller.mapper.RegisterProfessorAnalysisControllerMapper;
import br.com.tcc.project.controller.request.RegisterProfessorAnalysisRequest;
import br.com.tcc.project.domain.NotificationStatus;
import br.com.tcc.project.domain.Status;
import br.com.tcc.project.exception.documentation.DocApiResponsesError;
import br.com.tcc.project.gateway.CommandGateway;
import br.com.tcc.project.response.ProfessorAnaliseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Tag(name = "Professor")
@RestController
@Slf4j
@Validated
public class ProfessorAnalysisAllocationController {

  @Autowired @Setter private CommandGateway commandGateway;
  private final RegisterProfessorAnalysisControllerMapper mapper =
      Mappers.getMapper(RegisterProfessorAnalysisControllerMapper.class);
  private final ProfessorAnalysisDocumentMapper professorAnalysisMapper =
      Mappers.getMapper(ProfessorAnalysisDocumentMapper.class);

  @Operation(summary = "Register new analise", description = "Register new analise")
  @DocApiResponsesError
  @PostMapping("analises")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<ProfessorAnaliseResponse> registerProfessor(
      @Valid @RequestBody RegisterProfessorAnalysisRequest request) {
    AnalisesDocument analisesDocuments = commandGateway.invoke(FindAnalysisByOriginAndDestinyId.class,
            FindAnalysisByOriginAndDestinyId
                    .Request
                    .builder()
                    .disciplineDestinyId(request.getDisciplinaDestinoId())
                    .disciplineOriginId(request.getDisciplinaOrigemId())
                    .collegeOriginId(request.getFaculdadeOrigemId())
                    .collegeDestinyId(request.getFaculdadeDestinoId())
                    .build());

    if(analisesDocuments != null) {
      log.error(DisciplineEquivalenceErrors.DEE0008.message());
      throw new AnalysisAlreadyRegisteredException(DisciplineEquivalenceErrors.DEE0008.message(),
              DisciplineEquivalenceErrors.DEE0008.name(),
              DisciplineEquivalenceErrors.DEE0008.group());
    }

    CollegeDocument collegeOriginDocument =
        commandGateway.invoke(
            FindCollegeById.class,
            FindCollegeById.Request.builder().faculdadeId(request.getFaculdadeOrigemId()).build());

    CourseDocument courseOriginDocument =
        commandGateway.invoke(
            FindCourseById.class,
            FindCourseById.Request.builder().cursoId(request.getCursoOrigemId()).build());

    DisciplineDocument disciplineOriginDocument =
        commandGateway.invoke(
            FindDisciplineById.class,
            FindDisciplineById.Request.builder().id(request.getDisciplinaOrigemId()).build());

    CollegeDocument collegeDestinyDocument =
        commandGateway.invoke(
            FindCollegeById.class,
            FindCollegeById.Request.builder().faculdadeId(request.getFaculdadeDestinoId()).build());

    CourseDocument courseDestinyDocument =
        commandGateway.invoke(
            FindCourseById.class,
            FindCourseById.Request.builder().cursoId(request.getCursoDestinoId()).build());

    DisciplineDocument disciplineDestinyDocument =
        commandGateway.invoke(
            FindDisciplineById.class,
            FindDisciplineById.Request.builder().id(request.getDisciplinaDestinoId()).build());

    ProfessorDocument professorDocumennt =
        commandGateway.invoke(
            FindProfessorById.class,
            FindProfessorById.Request.builder().id(request.getProfessorId()).build());

    UsuarioDocument adminUser = commandGateway.invoke(
            FindUserByEmail.class,
            FindUserByEmail.Request.builder().email(request.getEmailAdministrador()).build());

    AnalisesDocument analisesDocument =
        commandGateway.invoke(
            RegisterProfessorAnalysis.class,
            mapper.map(
                request,
                collegeOriginDocument,
                courseOriginDocument,
                disciplineOriginDocument,
                collegeDestinyDocument,
                courseDestinyDocument,
                disciplineDestinyDocument,
                professorDocumennt,
                adminUser,
                request.getNomeAluno(),
                request.getEmailAluno(),
                null,
                Status.PENDING.name()));

    commandGateway.invoke(RegisterOpenAIEquivalenceAnalysis.class,
            RegisterOpenAIEquivalenceAnalysis
                    .Request
                    .builder()
                    .id(null)
                    .status(Status.PENDING)
                    .originDisciplineDocument(disciplineOriginDocument)
                    .destinyDisciplineDocument(disciplineDestinyDocument)
                    .createdAt(LocalDateTime.now())
                    .build()
    );

    commandGateway.invoke(RegisterProfessorNotification.class, mapper.map(analisesDocument, analisesDocument.getDataMaxima(), NotificationStatus.PENDING, null, professorDocumennt.getUsuario().getEmail()));

    return ResponseEntity.ok(professorAnalysisMapper.map(analisesDocument));
  }

  @Operation(summary = "Find analise by id", description = "Find analise by id")
  @DocApiResponsesError
  @GetMapping("analises/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<ProfessorAnaliseResponse> findProfessorById(
      @PathVariable(value = "id") Integer id) {

    AnalisesDocument professorDocument =
        commandGateway.invoke(
            FindProfessorAnalysisById.class,
            FindProfessorAnalysisById.Request.builder().id(id).build());

    return ResponseEntity.ok(professorAnalysisMapper.map(professorDocument));
  }

  @Operation(summary = "Find analise by id", description = "Find analise by id")
  @DocApiResponsesError
  @GetMapping("analises-professor/{id}")
  @PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
  public ResponseEntity<ProfessorAnaliseResponse> findAnaliseById(
          @PathVariable(value = "id") Integer id) {

    AnalisesDocument professorDocument =
            commandGateway.invoke(
                    FindProfessorAnalysisById.class,
                    FindProfessorAnalysisById.Request.builder().id(id).build());

    return ResponseEntity.ok(professorAnalysisMapper.map(professorDocument));
  }

  @Operation(
      summary = "Find discipline by college and course",
      description = "Find discipline by college and course")
  @DocApiResponsesError
  @GetMapping("analises")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<Page<ProfessorAnaliseResponse>>
      findAllProfessorByDiscplineCourseAndFaculdadeId(
          @RequestParam(value = "pagina", defaultValue = "0", required = false) Integer pagina,
          @RequestParam(value = "paginas", defaultValue = "25", required = false) Integer paginas,
          @RequestParam(value = "orderBy", defaultValue = "dataMaxima", required = false)
              String orderBy,
          @RequestParam(value = "direction", defaultValue = "ASC", required = false)
              String direction,
          @RequestParam(value = "nomeProfessor", required = false) String nomeProfessor) {

    Page<ProfessorAnaliseResponse> responses =
        commandGateway.invoke(
            FindAllProfessorAnalysis.class,
            FindAllProfessorAnalysis.Request.builder()
                .pagina(pagina)
                .paginas(paginas)
                .orderBy(orderBy)
                .direction(direction)
                .nomeProfessor(nomeProfessor)
                .build());

    return ResponseEntity.ok(responses);
  }

  @Operation(
          summary = "Find discipline by college and course",
          description = "Find discipline by college and course")
  @DocApiResponsesError
  @GetMapping("analises-professor")
  @PreAuthorize("hasAnyRole('ROLE_PROFESSOR')")
  public ResponseEntity<Page<ProfessorAnaliseResponse>> findAllAnalisesByProfessorEmail(
          @RequestParam(value = "pagina", defaultValue = "0", required = false) Integer pagina,
          @RequestParam(value = "paginas", defaultValue = "25", required = false) Integer paginas,
          @RequestParam(value = "orderBy", defaultValue = "data_maxima", required = false)
          String orderBy,
          @RequestParam(value = "direction", defaultValue = "ASC", required = false)
          String direction,
          @RequestParam(value = "emailProfessor", required = false) String emailProfessor
  ) {
    UsuarioDocument userDocument = commandGateway.invoke(FindUserByEmail.class, FindUserByEmail.Request.builder().email(emailProfessor).build());

    ProfessorDocument professorDocument = commandGateway.invoke(FindProfessorByUserId.class, FindProfessorByUserId.Request.builder().id(userDocument.getId()).build());

    Page<ProfessorAnaliseResponse> responses =
            commandGateway.invoke(
                    FindAllProfessorAnalysisByProfessorId.class,
                    FindAllProfessorAnalysisByProfessorId.Request.builder()
                            .pagina(pagina)
                            .paginas(paginas)
                            .orderBy(orderBy)
                            .direction(direction)
                            .professorId(professorDocument.getId())
                            .build());

    return ResponseEntity.ok(responses);
  }

  @Operation(
      summary = "Atualiza registro analise equivalencia",
      description = "Atualiza registro analise equivalencia")
  @DocApiResponsesError
  @PutMapping("analises/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<ProfessorAnaliseResponse> registerProfessor(
      @PathVariable(value = "id") Integer id,
      @Valid @RequestBody RegisterProfessorAnalysisRequest request) {

    AnalisesDocument actualAnalisesDocument = commandGateway.invoke(FindProfessorAnalysisById.class, FindProfessorAnalysisById.Request.builder().id(request.getId()).build());

    CollegeDocument collegeOriginDocument =
        commandGateway.invoke(
            FindCollegeById.class,
            FindCollegeById.Request.builder().faculdadeId(request.getFaculdadeOrigemId()).build());

    CourseDocument courseOriginDocument =
        commandGateway.invoke(
            FindCourseById.class,
            FindCourseById.Request.builder().cursoId(request.getCursoOrigemId()).build());

    DisciplineDocument disciplineOriginDocument =
        commandGateway.invoke(
            FindDisciplineById.class,
            FindDisciplineById.Request.builder().id(request.getDisciplinaOrigemId()).build());

    CollegeDocument collegeDestinyDocument =
        commandGateway.invoke(
            FindCollegeById.class,
            FindCollegeById.Request.builder().faculdadeId(request.getFaculdadeDestinoId()).build());

    CourseDocument courseDestinyDocument =
        commandGateway.invoke(
            FindCourseById.class,
            FindCourseById.Request.builder().cursoId(request.getCursoDestinoId()).build());

    DisciplineDocument disciplineDestinyDocument =
        commandGateway.invoke(
            FindDisciplineById.class,
            FindDisciplineById.Request.builder().id(request.getDisciplinaDestinoId()).build());

    ProfessorDocument professorDocumennt =
        commandGateway.invoke(
            FindProfessorById.class,
            FindProfessorById.Request.builder().id(request.getProfessorId()).build());

    UsuarioDocument adminUser = commandGateway.invoke(
            FindUserByEmail.class,
            FindUserByEmail.Request.builder().email(request.getEmailAdministrador()).build());

    AnalisesDocument analisesDocument =
        commandGateway.invoke(
            RegisterProfessorAnalysis.class,
            mapper.map(
                request,
                collegeOriginDocument,
                courseOriginDocument,
                disciplineOriginDocument,
                collegeDestinyDocument,
                courseDestinyDocument,
                disciplineDestinyDocument,
                professorDocumennt,
                adminUser,
                request.getNomeAluno(),
                request.getEmailAluno(),
                id,
                actualAnalisesDocument.getStatus())
        );

    NotificationDocument notificationDocument = commandGateway.invoke(
            FindPendingNotificationByAnalysisId.class,
            FindPendingNotificationByAnalysisId.Request.builder().analiseId(request.getId()).build());

    commandGateway.invoke(RegisterProfessorNotification.class, mapper.map(analisesDocument, analisesDocument.getDataMaxima(), NotificationStatus.PENDING, notificationDocument != null ? notificationDocument.getId() : null, professorDocumennt.getUsuario().getEmail()));

    return ResponseEntity.ok(professorAnalysisMapper.map(analisesDocument));
  }

  @Operation(summary = "Deleta analise", description = "Deleta analise")
  @DocApiResponsesError
  @DeleteMapping("analises/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<CollegeDocument> deleteAnaliseById(
          @PathVariable(value = "id") Integer id) {

    AnalisesDocument actualAnalisesDocument = commandGateway.invoke(FindProfessorAnalysisById.class, FindProfessorAnalysisById.Request.builder().id(id).build());

    if(!Status.PENDING.name().equals(actualAnalisesDocument.getStatus())) {
      throw new DuplicateEntryException(DisciplineEquivalenceErrors.DEE0012.message(),
              DisciplineEquivalenceErrors.DEE0012.name(),
              DisciplineEquivalenceErrors.DEE0012.group());
    }

    commandGateway.invoke(
            DeleteById.class,
            DeleteById.Request.builder().genericClass(br.com.tcc.project.command.repositoy.model.NotificationDocument.class).analysisId(id).build());

    commandGateway.invoke(
            DeleteById.class,
            DeleteById.Request.builder().genericClass(br.com.tcc.project.command.repositoy.model.EquivalenceDocument.class).analysisId(id).build());

    commandGateway.invoke(
            DeleteById.class,
            DeleteById.Request.builder().genericClass(br.com.tcc.project.command.repositoy.model.AnalisesDocument.class).id(id).build());

    return null;
  }

}
