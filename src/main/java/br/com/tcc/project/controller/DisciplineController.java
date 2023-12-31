package br.com.tcc.project.controller;

import br.com.tcc.project.command.*;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.EquivalenceAlreadyRegisteredException;
import br.com.tcc.project.command.repositoy.mapper.DisciplineDocumentMapper;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.command.repositoy.model.CourseDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.controller.mapper.RegisterDisciplineControllerMapper;
import br.com.tcc.project.controller.request.FindDisciplineByCollegeAndCourseRequest;
import br.com.tcc.project.controller.request.RegisterDisciplineRequest;
import br.com.tcc.project.domain.Status;
import br.com.tcc.project.exception.documentation.DocApiResponsesError;
import br.com.tcc.project.gateway.CommandGateway;
import br.com.tcc.project.response.DisciplineResponse;
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

@Tag(name = "Discipline")
@RestController
@Slf4j
@Validated
public class DisciplineController {

  @Autowired @Setter private RegisterDisciplineControllerMapper registerDisciplineMapper;

  @Autowired @Setter private CommandGateway commandGateway;
  private final DisciplineDocumentMapper mapper = Mappers.getMapper(DisciplineDocumentMapper.class);

  @Operation(summary = "Register new discipline", description = "Register new discipline")
  @DocApiResponsesError
  @PostMapping("disciplinas")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<DisciplineResponse> registerDiscipline(
      @Valid @RequestBody RegisterDisciplineRequest request) {

    DisciplineDocument existsDiscipline = commandGateway.invoke(
            FindDisciplineByCodeAndCollegeAndCourse.class,
            FindDisciplineByCodeAndCollegeAndCourse.Request.builder().codigo(request.getCodigoOrigem()).faculdadeId(request.getFaculdadeId()).cursoId(request.getCursoId()).build());

    if(existsDiscipline != null) {
      log.error(DisciplineEquivalenceErrors.DEE0011.message());
      throw new EquivalenceAlreadyRegisteredException(
              DisciplineEquivalenceErrors.DEE0011.message(),
              DisciplineEquivalenceErrors.DEE0011.name(),
              DisciplineEquivalenceErrors.DEE0011.group());
    }

    CollegeDocument collegeDocument =
        commandGateway.invoke(
            FindCollegeById.class,
            FindCollegeById.Request.builder().faculdadeId(request.getFaculdadeId()).build());

    CourseDocument courseDocument =
        commandGateway.invoke(
            FindCourseById.class,
            FindCourseById.Request.builder().cursoId(request.getCursoId()).build());

    DisciplineDocument disciplineDocument =
        commandGateway.invoke(
            RegisterDiscipline.class,
            registerDisciplineMapper.map(request, collegeDocument, courseDocument, null));

    return ResponseEntity.ok(mapper.map(disciplineDocument));
  }

  @Operation(summary = "Find discipline by id", description = "Find discipline by id")
  @DocApiResponsesError
  @GetMapping("disciplinas/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<DisciplineResponse> findDisciplineById(
      @PathVariable(value = "id") Integer id) {

    DisciplineDocument disciplineDocument =
        commandGateway.invoke(
            FindDisciplineById.class, FindDisciplineById.Request.builder().id(id).build());

    return ResponseEntity.ok(mapper.map(disciplineDocument));
  }

  @Operation(
      summary = "Find discipline by college and course",
      description = "Find discipline by college and course")
  @DocApiResponsesError
  @GetMapping("disciplinas")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<Page<DisciplineResponse>> findAllDisciplineByCollegeAndCourse(
      @RequestParam(value = "pagina", defaultValue = "0", required = false) Integer pagina,
      @RequestParam(value = "paginas", defaultValue = "25", required = false) Integer paginas,
      @RequestParam(value = "orderBy", defaultValue = "nome", required = false) String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction,
      @RequestParam(value = "nome", required = false) String nome,
      @RequestParam(value = "faculdadeId", required = false) String faculdadeId,
      @RequestParam(value = "cursoId", required = false) String cursoId) {
    Page<DisciplineResponse> colleges =
        commandGateway.invoke(
            FindAllDisciplineByCollegeAndCourse.class,
            FindAllDisciplineByCollegeAndCourse.Request.builder()
                .pagina(pagina)
                .paginas(paginas)
                .orderBy(orderBy)
                .direction(direction)
                .faculdadeId(faculdadeId)
                .cursoId(cursoId)
                .nome(nome)
                .build());

    return ResponseEntity.ok(colleges);
  }

  @Operation(summary = "Register new discipline", description = "Register new discipline")
  @DocApiResponsesError
  @PutMapping("disciplinas/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<DisciplineResponse> updateDiscipline(
      @PathVariable(value = "id") Integer id,
      @Valid @RequestBody RegisterDisciplineRequest request) {

    CollegeDocument collegeDocument =
        commandGateway.invoke(
            FindCollegeById.class,
            FindCollegeById.Request.builder().faculdadeId(request.getFaculdadeId()).build());

    CourseDocument courseDocument =
        commandGateway.invoke(
            FindCourseById.class,
            FindCourseById.Request.builder().cursoId(request.getCursoId()).build());

    DisciplineDocument disciplineDocument =
        commandGateway.invoke(
            RegisterDiscipline.class,
            registerDisciplineMapper.map(request, collegeDocument, courseDocument, id));

    commandGateway.invoke(
            RegisterDisciplineModification.class,
            RegisterDisciplineModification.Request.builder().disciplineDocument(disciplineDocument).status(Status.PENDING.name()).id(null).build());

    return ResponseEntity.ok(mapper.map(disciplineDocument));
  }
  @Operation(summary = "Deleta disciplina", description = "Deleta disciplina")
  @DocApiResponsesError
  @DeleteMapping("disciplinas/{id}")
  public ResponseEntity<CollegeDocument> deleteCourseById(
          @PathVariable(value = "id") Integer id) {
    commandGateway.invoke(
            DeleteById.class,
            DeleteById.Request.builder().genericClass(br.com.tcc.project.command.repositoy.model.DisciplineDocument.class).id(id).build());
    return null;
  }

}
